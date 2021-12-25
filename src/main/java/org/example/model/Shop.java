package org.example.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import org.example.Constants;

import java.util.ArrayList;


public class Shop {
    @CsvBindByName(column = Constants.ID)
    long id;
    @CsvBindByName(column = Constants.NAME)
    String name;
    @CsvBindByName(column = Constants.GUITAR_ID)
    long guitarId;
    @CsvBindByName(column = Constants.PRICES_IDS)
    ArrayList<Long> pricesId;

    public Shop() {
    }


    public Shop(String name, long guitarId, ArrayList<Long> pricesId) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.guitarId = guitarId;
        this.pricesId = pricesId;
    }

    public Shop(long id, String name, long guitarId, ArrayList<Long> pricesId) {
        this.id = id;
        this.name = name;
        this.guitarId = guitarId;
        this.pricesId = pricesId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGuitarId() {
        return guitarId;
    }

    public void setGuitarId(long guitarId) {
        this.guitarId = guitarId;
    }

    public ArrayList<Long> getPricesId() {
        return pricesId;
    }

    public void setPricesId(ArrayList<Long> pricesId) {
        this.pricesId = pricesId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", guitarId=" + guitarId +
                ", pricesId=" + pricesId +
                '}';
    }
}
