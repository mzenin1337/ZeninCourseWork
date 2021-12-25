package org.example.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;
import org.example.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DbDataProvider implements InterfaceDataProvider{
    private static final Logger logger = LogManager.getLogger(DbDataProvider.class);
    @Override
    public boolean createGuitar(String name, boolean choice, String[] args) {
        boolean save;
        Guitar guitar = new Guitar(name);
        if (choice){
            save = createOwnInfo(guitar, args[0], args[1]);
        } else {
            save = createWithParameters(guitar, args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), Boolean.parseBoolean(args[5]));
        }
        return save;
    }

    @Override
    public boolean createOwnInfo(Guitar guitar, String info, String guitarType) {
        OwnGuitar ownGuitar = new OwnGuitar(guitar.getName(), info, guitarType);
        String query = String.format(Constants.INSERT_OWN_GUITAR, ownGuitar.getId(), ownGuitar.getName(), ownGuitar.getInfo(), ownGuitar.getGuitarType());
        boolean save = execute(Constants.CREATE_TABLE_OWN_GUITAR, query);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, ownGuitar);
        return save;
    }

    @Override
    public boolean createWithParameters(Guitar guitar, String manufacturer, String upperDeckMaterial, String lowerDeckMaterial, String shellMaterial, int numberOfFrets, boolean cases) {
        GuitarWithParameters guitarWP = new GuitarWithParameters(guitar.getName(), manufacturer,
                upperDeckMaterial, lowerDeckMaterial, shellMaterial, numberOfFrets, cases);
        String query = String.format(Constants.INSERT_GUITAR_WITH_PARAMETERS, guitarWP.getId(), guitarWP.getName(),
                guitarWP.getManufacturer(), guitarWP.getUpperDeckMaterial(), guitarWP.getLowerDeckMaterial(),
                guitarWP.getShellMaterial(), guitarWP.getNumberOfFrets(), guitarWP.isCases());
        boolean save = execute(Constants.CREATE_TABLE_GUITAR_WITH_PARAMETERS, query);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, guitarWP);
        return save;
    }

    @Override
    public boolean createPrice(float cost, String dateString, boolean convert, String[] args) {
        float sum = cost;
        String date = chooseDate(dateString);
        if (convert){
            sum = convertPrice(cost, args[0]);
        }
        Price price = new Price(sum, date);
        String query = String.format(Constants.INSERT_PRICE, price.getId(), price.getPrice(), price.getDate());
        boolean save = execute(Constants.CREATE_TABLE_PRICE, query);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, price);
        return save;
    }

    @Override
    public float convertPrice(float cost, String type) {
        float price = cost;
        switch (type){
            case Constants.USD:
                price = cost*74;
                break;
            case Constants.EURO:
                price = cost*84;
                break;
            default:
                logger.error(Constants.CONVERT_INFO);
                return -1;
        }
        return price;
    }

    @Override
    public String chooseDate(String dateString) {
        String date;
        if (dateString.matches(Constants.REGEX_DATE)){
            date = dateString;
        } else {
            try {
                DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
                date = dateFormat.format(Calendar.getInstance().getTime());
            } catch (Exception e) {
                logger.error(e);
                return "";
            }
        }
        return date;
    }

    public boolean execute(String table, String query){
        boolean isCreated = false;
        try {
            Class.forName(Constants.DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        try (Connection connection = DriverManager.getConnection(Constants.ADDRESS, Constants.USER, Constants.PASSWORD)){
            Statement statement = connection.createStatement();
            statement.execute(table);
            statement.execute(query);
            isCreated = true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return isCreated;
    }
}
