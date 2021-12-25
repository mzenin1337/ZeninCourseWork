package org.example.model;

import com.opencsv.bean.CsvBindByName;
import org.example.Constants;

/**
 * Класс Guitar
 * Класс родитель для гитар
 */
public class Guitar {
    @CsvBindByName(column = Constants.ID)
    long id;
    @CsvBindByName(column = Constants.NAME)
    String name;

    public Guitar() {
    }


    public Guitar(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
    }

    public Guitar(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Guitar{" +
                "name='" + name + '\'' +
                '}';
    }
}
