package org.example.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;
import org.example.model.*;
import org.example.model.xmlwrapper.XmlGuitarWithParameters;
import org.example.model.xmlwrapper.XmlOwnGuitar;
import org.example.model.xmlwrapper.XmlPrice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlDataProvider implements InterfaceDataProvider{
    private static final Logger logger = LogManager.getLogger(XmlDataProvider.class);
    JAXBContext context;
    @Override
    public boolean createGuitar(String name, boolean choice, String[] args) {
        boolean save;
        Guitar guitar = new Guitar(name);
        if (choice){
            save = createOwnInfo(guitar, args[0], args[1]);
        } else {
            save = createWithParameters(guitar, args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), Boolean.parseBoolean(args[5]));
        }
        return save;
    }

    @Override
    public boolean createOwnInfo(Guitar guitar, String info, String guitarType) {
        OwnGuitar ownGuitar = new OwnGuitar(guitar.getName(), info, guitarType);
        XmlOwnGuitar xmlOwnGuitar = new XmlOwnGuitar();
        List<OwnGuitar> list = new ArrayList<>();
        File file = new File(Constants.XML_OWN_GUITAR);
        if (file.exists()) {
            try {
                context = JAXBContext.newInstance(XmlOwnGuitar.class);
                xmlOwnGuitar = (XmlOwnGuitar) context.createUnmarshaller().unmarshal(file);
            } catch (JAXBException e) {
                logger.error(e);
                return false;
            }
            list = xmlOwnGuitar.getGuitars();
        }
        list.add(ownGuitar);
        try {
            context = JAXBContext.newInstance(XmlOwnGuitar.class);
            xmlOwnGuitar.setGuitars(list);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xmlOwnGuitar, new File(Constants.XML_OWN_GUITAR));
            InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, ownGuitar);
            return true;
        } catch (JAXBException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean createWithParameters(Guitar guitar, String manufacturer, String upperDeckMaterial, String lowerDeckMaterial, String shellMaterial, int numberOfFrets, boolean cases) {
        GuitarWithParameters guitarWithParameters = new GuitarWithParameters(guitar.getName(), manufacturer,
                upperDeckMaterial, lowerDeckMaterial, shellMaterial, numberOfFrets, cases);
        XmlGuitarWithParameters xmlGuitarWithParameters = new XmlGuitarWithParameters();
        List<GuitarWithParameters> list = new ArrayList<>();
        File file = new File(Constants.XML_GUITAR_WITH_PARAMETERS);
        if (file.exists()) {
            try {
                context = JAXBContext.newInstance(XmlGuitarWithParameters.class);
                xmlGuitarWithParameters = (XmlGuitarWithParameters) context.createUnmarshaller().unmarshal(file);
            } catch (JAXBException e) {
                logger.error(e);
                return false;
            }
            list = xmlGuitarWithParameters.getGuitars();
        }
        list.add(guitarWithParameters);
        try {
            context = JAXBContext.newInstance(XmlGuitarWithParameters.class);
            xmlGuitarWithParameters.setGuitars(list);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xmlGuitarWithParameters, new File(Constants.XML_GUITAR_WITH_PARAMETERS));
            InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, guitarWithParameters);
            return true;
        } catch (JAXBException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean createPrice(float cost, String dateString, boolean convert, String[] args) {
        float sum = cost;
        String date = chooseDate(dateString);
        if (convert){
            sum = convertPrice(cost, args[0]);
        }
        Price price = new Price(sum, date);
        XmlPrice xmlPrice = new XmlPrice();
        List<Price> list = new ArrayList<>();
        File file = new File(Constants.XML_PRICE);
        if (file.exists()) {
            try {
                context = JAXBContext.newInstance(XmlPrice.class);
                xmlPrice = (XmlPrice) context.createUnmarshaller().unmarshal(file);
            } catch (JAXBException e) {
                logger.error(e);
                return false;
            }
            list = xmlPrice.getPrices();
        }
        list.add(price);
        try {
            context = JAXBContext.newInstance(XmlPrice.class);
            xmlPrice.setPrices(list);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xmlPrice, new File(Constants.XML_PRICE));
            InterfaceDataProvider.saveHistory(getClass().getName(), HistoryContent.Status.SUCCESS, price);
            return true;
        } catch (JAXBException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public float convertPrice(float cost, String type) {
        float price = cost;
        switch (type){
            case Constants.USD:
                price = cost*74;
                break;
            case Constants.EURO:
                price = cost*84;
                break;
            default:
                logger.error(Constants.CONVERT_INFO);
                return -1;
        }
        return price;
    }

    @Override
    public String chooseDate(String dateString) {
        String date;
        if (dateString.matches(Constants.REGEX_DATE)){
            date = dateString;
        } else {
            try {
                DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
                date = dateFormat.format(Calendar.getInstance().getTime());
            } catch (Exception e) {
                logger.error(e);
                return "";
            }
        }
        return date;
    }
}
