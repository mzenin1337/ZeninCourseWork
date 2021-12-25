package org.example.model.xmlwrapper;

import org.example.Constants;
import org.example.model.GuitarWithParameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Обертка для Xml файлов
 */
@XmlRootElement
public class XmlGuitarWithParameters {
    private List<GuitarWithParameters> guitars;

    public List<GuitarWithParameters> getGuitars() {
        return guitars;
    }
    @XmlElement(name = Constants.XML_WRAPPER_GUITARS)
    public void setGuitars(List<GuitarWithParameters> guitars) {
        this.guitars = guitars;
    }

    @Override
    public String toString() {
        return guitars.toString();
    }
}
