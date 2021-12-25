package org.example.model;

import com.opencsv.bean.CsvBindByName;
import org.example.Constants;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс GuitarWithParameters
 * Используется для параметров гитары
 */
@XmlRootElement
public class GuitarWithParameters extends Guitar{
    @CsvBindByName(column = Constants.MANUFACTURER)
    String manufacturer;
    @CsvBindByName(column = Constants.UPPER_DECK_MATERIAL)
    String upperDeckMaterial;
    @CsvBindByName(column = Constants.LOWER_DECK_MATERIAL)
    String lowerDeckMaterial;
    @CsvBindByName(column = Constants.SHELL_MATERIAL)
    String shellMaterial;
    @CsvBindByName(column = Constants.NUMBER_OF_FRETS)
    int numberOfFrets;
    @CsvBindByName(column = Constants.CASES)
    boolean cases;

    public GuitarWithParameters() {
    }

    /**
     * Конструктор для создания гитары с параметрами
     * @param name Название гитары
     * @param manufacturer Производитель
     * @param upperDeckMaterial Материал верхней деки
     * @param lowerDeckMaterial Материал нижней деки
     * @param shellMaterial Материал корпуса
     * @param numberOfFrets Количество ладов
     * @param cases Наличие чехла/кейса
     */
    public GuitarWithParameters(String name, String manufacturer, String upperDeckMaterial, String lowerDeckMaterial, String shellMaterial, int numberOfFrets, boolean cases) {
        super(name);
        this.manufacturer = manufacturer;
        this.upperDeckMaterial = upperDeckMaterial;
        this.lowerDeckMaterial = lowerDeckMaterial;
        this.shellMaterial = shellMaterial;
        this.numberOfFrets = numberOfFrets;
        this.cases = cases;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUpperDeckMaterial() {
        return upperDeckMaterial;
    }

    public void setUpperDeckMaterial(String upperDeckMaterial) {
        this.upperDeckMaterial = upperDeckMaterial;
    }

    public String getLowerDeckMaterial() {
        return lowerDeckMaterial;
    }

    public void setLowerDeckMaterial(String lowerDeckMaterial) {
        this.lowerDeckMaterial = lowerDeckMaterial;
    }

    public String getShellMaterial() {
        return shellMaterial;
    }

    public void setShellMaterial(String shellMaterial) {
        this.shellMaterial = shellMaterial;
    }

    public int getNumberOfFrets() {
        return numberOfFrets;
    }

    public void setNumberOfFrets(int numberOfFrets) {
        this.numberOfFrets = numberOfFrets;
    }

    public boolean isCases() {
        return cases;
    }

    public void setCases(boolean cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {
        return "GuitarWithParameters{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", upperDeckMaterial='" + upperDeckMaterial + '\'' +
                ", lowerDeckMaterial='" + lowerDeckMaterial + '\'' +
                ", shellMaterial='" + shellMaterial + '\'' +
                ", numberOfFrets=" + numberOfFrets +
                ", cases=" + cases +
                '}';
    }
}
