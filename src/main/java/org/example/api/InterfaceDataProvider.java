package org.example.api;

import com.google.gson.Gson;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.Constants;
import org.example.model.HistoryContent;
import org.example.model.Guitar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Интервейс дата-провайдера
 */
///////////////////////////
public interface InterfaceDataProvider {
    /**
     * Метод создания гитары
     * @param name Название гитары
     * @param choice Выбор, какую гитару создать
     * @param args Передача параметров войств гитары. Передаёт дополнительные параметры в случае choice == false
     * @return Результат выполнения операции
     */
    boolean createGuitar(String name, boolean choice, String[] args);

    /**
     * Метод добавления собственной информации о гитаре
     * @param guitar Название гитары
     * @param info Информация о гитаре
     * @param guitarType Тип гитары
     * @return Результат выполнения операции
     */
    boolean createOwnInfo(Guitar guitar, String info, String guitarType);

    /**
     * Метод создания гитары с параметрами
     * @param guitar Название гитары
     * @param manufacturer Производитель
     * @param upperDeckMaterial Материал верхней деки
     * @param lowerDeckMaterial Материал нижней деки
     * @param shellMaterial Материал корпуса
     * @param numberOfFrets Количество ладов
     * @param cases наличие чехла/кейса
     * @return Результат выполнения операции
     */
    boolean createWithParameters(Guitar guitar, String manufacturer, String upperDeckMaterial, String lowerDeckMaterial,
                                 String shellMaterial, int numberOfFrets, boolean cases);

    /**
     * Метод создания цены для гитары
     * @param cost Стоимость гитары
     * @param dateString Дата создания цены
     * @param convert Проверка необходимости конвертации
     * @param args Передача параметров для создания цены
     * @return Результат выполнения операции
     */
    boolean createPrice(float cost, String dateString, boolean convert, String[] args);

    /**
     * Метод конвертации цены на гитару в рубли
     * @param cost Стоимость гитары
     * @param type Тип валюты (usd, euro)
     * @return
     */
    float convertPrice(float cost, String type);

    /**
     * Метод для выбора даты создания гитары
     * @param dateString Дата создания
     * @return Возвращение даты в необходимом формате
     */
    String chooseDate(String dateString);

    /**
     * Метод реализации взаимодейтсвия данных с MongoDB
     * @param className Имя класса в котором был вызван метод для изменения объекта
     * @param status Статус изменения: Удачно или Неудачно
     * @param object Объект для конвертации данных в Json формат
     * @return Результат выполнения операции
     */
    static boolean saveHistory(String className, HistoryContent.Status status, Object object){
        try (MongoClient mongoClient = MongoClients.create(Constants.MONGO_DB)) {
            MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
            try{
                database.createCollection(Constants.COLLECTION);
            } catch (MongoCommandException ignored) {

            }
            String date = new SimpleDateFormat(Constants.DATE_PATTERN).format(new Date());
            HistoryContent historyContent = new HistoryContent(className, date, status, new Gson().toJson(object));
            MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION);
            collection.insertOne(Document.parse(new Gson().toJson(historyContent)));
        }
        return true;
    }
}
