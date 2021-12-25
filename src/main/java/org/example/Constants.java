package org.example;

public class Constants {
    public static final String PROPERTIES = "properties";
    public static final String ACTOR = "System";
    public static final String MONGO_DB = "mongodb://localhost:27017";
    public static final String DB_NAME = "Misha";
    public static final String COLLECTION = "history";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MANUFACTURER = "manufacturer";
    public static final String UPPER_DECK_MATERIAL = "upperDeckMaterial";
    public static final String LOWER_DECK_MATERIAL = "lowerDeckMaterial";
    public static final String SHELL_MATERIAL = "shellMaterial";
    public static final String NUMBER_OF_FRETS = "numberOfFrets";
    public static final String CASES = "cases";
    public static final String INFO = "info";
    public static final String GUITAR_TYPE = "guitarType";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String GUITAR_ID = "guitarId";
    public static final String PRICES_IDS = "pricesId";

    public static final String USD = "usd";
    public static final String EURO = "euro";
    public static final String CONVERT_INFO = "no such currency";

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    public static final String XML_WRAPPER_GUITARS = "guitar";
    public static final String XML_WRAPPER_PRICES = "price";

    public static final String CSV_OWN_GUITAR = "./src/main/resources/own guitar.csv";
    public static final String CSV_GUITAR_WITH_PARAMETERS = "./src/main/resources/guitar with parameters.csv";
    public static final String CSV_PRICE = "./src/main/resources/price.csv";

    public static final String XML_OWN_GUITAR = "./src/main/resources/own guitar.xml";
    public static final String XML_GUITAR_WITH_PARAMETERS = "./src/main/resources/guitar with parameters.xml";
    public static final String XML_PRICE = "./src/main/resources/price.xml";

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String ADDRESS = "jdbc:mysql://localhost:3306/Misha";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static final String CREATE_TABLE_GUITAR_WITH_PARAMETERS = "CREATE TABLE IF NOT EXISTS GUITAR_WITH_PARAMETERS (" +
            "id BIGINT, " +
            "name TEXT, " +
            "manufacturer TEXT, " +
            "upperDeckMaterial TEXT, " +
            "lowerDeckMaterial TEXT, " +
            "shellMaterial TEXT, " +
            "numberOfFrets INT, " +
            "cases TEXT);";
    public static final String CREATE_TABLE_OWN_GUITAR = "CREATE TABLE IF NOT EXISTS OWN_GUITAR (" +
            "id BIGINT, " +
            "name TEXT, " +
            "info TEXT, " +
            "guitarType TEXT);";
    public static final String CREATE_TABLE_PRICE = "CREATE TABLE IF NOT EXISTS PRICE (" +
            "id BIGINT, " +
            "price TEXT, " +
            "date TEXT);";
    public static final String INSERT_GUITAR_WITH_PARAMETERS = "INSERT INTO GUITAR_WITH_PARAMETERS VALUES " +
            "(%d, '%s', '%s', '%s', '%s', '%s', %d, '%s');";
    public static final String INSERT_OWN_GUITAR = "INSERT INTO OWN_GUITAR VALUES " +
            "(%d, '%s', '%s', '%s');";
    public static final String INSERT_PRICE = "INSERT INTO PRICE VALUES " +
            "(%d, '%s', '%s');";
}
