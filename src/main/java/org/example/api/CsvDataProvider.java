package org.example.api;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;
import org.example.model.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvDataProvider implements InterfaceDataProvider{
    private static final Logger logger = LogManager.getLogger(CsvDataProvider.class);

    public CsvDataProvider() {
    }

    @Override
    public boolean createGuitar(String name, boolean choice, String[] args) {
        boolean save = false;
        Guitar guitar = new Guitar(name);
        if (args == null){
            return save;
        }
        if (choice){
            save = createOwnInfo(guitar, args[0], args[1]);
        } else {
            save = createWithParameters(guitar, args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), Boolean.parseBoolean(args[5]));
        }
        return save;
    }

    @Override
    public boolean createOwnInfo(Guitar guitar, String info, String guitarType) {
        if (guitar == null){
            return false;
        }
        OwnGuitar ownGuitar = new OwnGuitar(guitar.getName(), info, guitarType);
        List<Object> list = new ArrayList<>();
        if (read(OwnGuitar.class, Constants.CSV_OWN_GUITAR).isPresent()){
            list = read(OwnGuitar.class, Constants.CSV_OWN_GUITAR).get();
        }
        list.add(ownGuitar);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, ownGuitar);
        return save(list, Constants.CSV_OWN_GUITAR);
    }

    @Override
    public boolean createWithParameters(Guitar guitar, String manufacturer, String upperDeckMaterial,
                                        String lowerDeckMaterial, String shellMaterial, int numberOfFrets, boolean cases) {
        if (guitar == null){
            return false;
        }
        GuitarWithParameters guitarWithParameters = new GuitarWithParameters(guitar.getName(), manufacturer,
                upperDeckMaterial, lowerDeckMaterial, shellMaterial, numberOfFrets, cases);
        List<Object> list = new ArrayList<>();
        if (read(GuitarWithParameters.class, Constants.CSV_GUITAR_WITH_PARAMETERS).isPresent()){
            list = read(GuitarWithParameters.class, Constants.CSV_GUITAR_WITH_PARAMETERS).get();
        }
        list.add(guitarWithParameters);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, guitarWithParameters);
        return save(list, Constants.CSV_GUITAR_WITH_PARAMETERS);
    }

    @Override
    public boolean createPrice(float cost, String dateString, boolean convert, String[] args) {
        float sum = cost;
        String date = chooseDate(dateString);
        if (args == null){
            return false;
        }
        if (convert){
            sum = convertPrice(cost, args[0]);
        }
        Price price = new Price(sum, date);
        List<Object> list = new ArrayList<>();
        if (read(Price.class, Constants.CSV_PRICE).isPresent()){
            list = read(Price.class, Constants.CSV_PRICE).get();
        }
        list.add(price);
        InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, price);
        return save(list, Constants.CSV_PRICE);
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


    public <T> boolean save(List<T> list, String path){
        boolean isSaved;
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(path));
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(csvWriter).build();
            beanToCsv.write(list);
            csvWriter.close();
            isSaved = true;
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            logger.error(e);
            isSaved = false;
        }
        return isSaved;
    }

    public <T> Optional<List<T>> read(Class<?> type, String path){
        List<T> list;
        File file = new File(path);
        Optional<List<T>> optionalList;
        if (file.exists() && (file.length() != 0)){
            try {
                list = new CsvToBeanBuilder<T>(new FileReader(path)).withType((Class<? extends T>) type).build().parse();
                optionalList = Optional.of(list);
            } catch (FileNotFoundException e) {
                logger.error(e);
                optionalList = Optional.empty();
            }
        } else{
            optionalList = Optional.empty();
        }
        return optionalList;
    }
}
