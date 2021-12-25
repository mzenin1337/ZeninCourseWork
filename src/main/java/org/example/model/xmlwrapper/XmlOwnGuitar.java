package org.example.model.xmlwrapper;

import org.example.Constants;
import org.example.model.OwnGuitar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Обертка для Xml файлов
 */
@XmlRootElement
public class XmlOwnGuitar {
    private List<OwnGuitar> guitars;

    public List<OwnGuitar> getGuitars() {
        return guitars;
    }
    @XmlElement(name = Constants.XML_WRAPPER_GUITARS)
    public void setGuitars(List<OwnGuitar> guitars) {
        this.guitars = guitars;
    }

    @Override
    public String toString() {
        return guitars.toString();
    }
}
