package com.coki.soapclient;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class SoapXmlUnmarshaller {

    public static <T> T unmarshal(String response, Class<T> tClass) {
        try {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(response));
            xsr.nextTag();
            while (!xsr.getLocalName().equalsIgnoreCase(tClass.getSimpleName())) {
                xsr.nextTag();
            }

            JAXBContext jc = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement<T> je = unmarshaller.unmarshal(xsr, tClass);
            return je.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
