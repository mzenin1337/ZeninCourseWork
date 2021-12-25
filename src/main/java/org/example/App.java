package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.CsvDataProvider;
import org.example.api.DbDataProvider;
import org.example.api.InterfaceDataProvider;
import org.example.api.XmlDataProvider;


public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args ) {
        if (args.length == 0){
            logger.error("you have not chosen a data provider");
            return;
        }
        InterfaceDataProvider dataProvider;
        switch (args[0]){
            case "csv":
                dataProvider = new CsvDataProvider();
                break;
            case "xml":
                dataProvider = new XmlDataProvider();
                break;
            case "db":
                dataProvider = new DbDataProvider();
                break;
            default:
                logger.error("the data provider is written incorrectly");
                return;
        }
        if (args.length == 1){
            logger.error("you didn't choose the method name");
            return;
        }
        switch (args[1]){
            case "createGuitar":
                if (args.length < 6){
                    logger.error("insufficient number of parameters");
                    return;
                }
                String name;
                boolean choice;
                name = args[2];
                switch (args[3]){
                    case "true":
                        choice = true;
                        break;
                    case "false":
                        choice = false;
                        break;
                    default:
                        logger.error("failed to convert boolean");
                        return;
                }
                if (choice){
                    String info = args[4];
                    String guitarType = args[5];
                    dataProvider.createGuitar(name, true, new String[]{info, guitarType});
                } else {
                    if (args.length < 10){
                        logger.error("insufficient number of parameters");
                        return;
                    }
                    String manufacturer = args[4];
                    String upperDeckMaterial = args[5];
                    String lowerDeckMaterial = args[6];
                    String shellMaterial = args[7];
                    int numberOfFrets;
                    try {
                        numberOfFrets = Integer.parseInt(args[8]);
                    } catch (NumberFormatException e) {
                        logger.error(e);
                        return;
                    }
                    boolean cases;
                    switch (args[9]){
                        case "true":
                            cases = true;
                            break;
                        case "false":
                            cases = false;
                            break;
                        default:
                            logger.error("could not convert boolean");
                            return;
                    }
                    dataProvider.createGuitar(name, false, new String[]{manufacturer, upperDeckMaterial, lowerDeckMaterial,
                            shellMaterial, String.valueOf(numberOfFrets), String.valueOf(cases)});
                }
                break;
            case "createPrice":
                if (args.length < 5){
                    logger.error("insufficient number of parameters");
                    return;
                }
                float cost;
                String date;
                boolean convert;
                try {
                    cost = Float.parseFloat(args[2]);
                } catch (NumberFormatException e) {
                    logger.error(e);
                    return;
                }
                date = args[3];
                switch (args[4]){
                    case "true":
                        convert = true;
                        break;
                    case "false":
                        convert = false;
                        break;
                    default:
                        logger.error("could not convert boolean");
                        return;
                }
                if (convert){
                    if (args.length < 6){
                        logger.error("insufficient number of parameters");
                        return;
                    }
                    String type = args[5];
                    dataProvider.createPrice(cost, date, true, new String[]{type});
                } else {
                    dataProvider.createPrice(cost, date, false, new String[]{});
                }
                break;
            default:
                logger.error("the method name is written incorrectly");
        }
    }
}
