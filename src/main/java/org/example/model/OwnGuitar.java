package org.example.model;

import com.opencsv.bean.CsvBindByName;
import org.example.Constants;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс OwnGuitar
 * Используется для создания собственной гитары
 */
@XmlRootElement
public class OwnGuitar extends Guitar{
    @CsvBindByName(column = Constants.INFO)
    String info;
    @CsvBindByName(column = Constants.GUITAR_TYPE)
    String guitarType;

    public OwnGuitar() {
    }

    /**
     * Конструктор для создания гитары
     * @param name Название гитары
     * @param info Информация о гитаре
     * @param guitarType Тип гитары
     */
    public OwnGuitar(String name, String info, String guitarType) {
        super(name);
        this.info = info;
        this.guitarType = guitarType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(String guitarType) {
        this.guitarType = guitarType;
    }

    @Override
    public String toString() {
        return "OwnGuitar{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", guitarType='" + guitarType + '\'' +
                '}';
    }
}
