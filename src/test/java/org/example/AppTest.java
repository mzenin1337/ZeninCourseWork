package org.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.CsvDataProvider;
import org.example.api.DbDataProvider;
import org.example.api.InterfaceDataProvider;
import org.example.api.XmlDataProvider;
import org.example.model.Guitar;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final Logger logger = LogManager.getLogger(AppTest.class);
    InterfaceDataProvider dataProvider = new CsvDataProvider();

    @Test
    public void createGuitarPositive(){
        assertTrue(dataProvider.createGuitar("name", true, new String[]{"1", "2"}));
    }

    @Test
    public void createGuitarNegative(){
        assertFalse(dataProvider.createGuitar("name", true, null));
    }

    @Test
    public void createOwnInfoPositive(){
        Guitar guitar = new Guitar(1, "name");
        assertTrue(dataProvider.createOwnInfo(guitar, "info", "guitarType"));
    }

    @Test
    public void createOwnInfoNegative(){
        assertFalse(dataProvider.createOwnInfo(null, "info", "guitarType"));
    }

    @Test
    public void createWithParametersPositive(){
        Guitar guitar = new Guitar(1, "name");
        assertTrue(dataProvider.createWithParameters(guitar, "manufacturer", "upperDeckMaterial","lowerDeckMaterial", "shellMaterial", 10, true));
    }

    @Test
    public void createWithParametersNegative(){
        assertFalse(dataProvider.createWithParameters(null, "manufacturer", "upperDeckMaterial","lowerDeckMaterial", "shellMaterial", 10, true));
    }

    @Test
    public void createPricePositive(){
        assertTrue(dataProvider.createPrice((float) 10.1, "2021/12/20", false, new String[]{}));
    }

    @Test
    public void createPriceNegative(){
        assertFalse(dataProvider.createPrice((float) 10.1, "", false, null));
    }

    @Test
    public void convertPricePositive(){
        boolean ok = true;
        if (dataProvider.convertPrice(100, "euro") == -1) {
            ok = false;
        }
        assertTrue(ok);
    }

    @Test
    public void convertPriseNegative(){
        boolean ok = true;
        if(dataProvider.convertPrice(100, "noExistedCurrency") == -1){
            ok = false;
        }
        assertFalse(ok);
    }

    @Test
    public void chooseDatePositive(){
        boolean ok = true;
        if(dataProvider.chooseDate("12/12/2020").isEmpty()){
            ok = false;
        }
        assertTrue(ok);
    }

    @Test
    public void chooseDateNegative(){
        boolean ok = true;
        if(dataProvider.chooseDate("12").isEmpty()){
            ok = false;
        }
        assertFalse(ok);
    }

}
