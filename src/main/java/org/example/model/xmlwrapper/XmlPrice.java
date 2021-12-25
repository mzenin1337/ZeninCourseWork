package org.example.model.xmlwrapper;

import org.example.Constants;
import org.example.model.Price;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Обертка для Xml файлов
 */
@XmlRootElement
public class XmlPrice {
    private List<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }
    @XmlElement(name = Constants.XML_WRAPPER_PRICES)
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return prices.toString();
    }
}
