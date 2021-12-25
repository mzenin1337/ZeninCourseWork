package org.example.model;

import com.opencsv.bean.CsvBindByName;
import org.example.Constants;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Price {
    @CsvBindByName(column = Constants.ID)
    long id;
    @CsvBindByName(column = Constants.PRICE)
    float price;
    @CsvBindByName(column = Constants.DATE)
    String date;

    public Price() {
    }


    public Price(float price, String date) {
        this.id = System.currentTimeMillis();
        this.price = price;
        this.date = date;
    }

    public Price(long id, float price, String date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
