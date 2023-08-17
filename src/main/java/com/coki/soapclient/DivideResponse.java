package com.coki.soapclient;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DivideResponse", namespace = "http://tempuri.org/")
public class DivideResponse {

    private int divideResult;

    @XmlElement(name = "DivideResult", namespace = "http://tempuri.org/")
    public int getDivideResult() {
        return divideResult;
    }

    public void setDivideResult(int divideResult) {
        this.divideResult = divideResult;
    }
}
