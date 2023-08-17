package com.coki.soapclient;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MultiplyResponse", namespace = "http://tempuri.org/")
public class MultiplyResponse {

    private int MultiplyResult;

    @XmlElement(name = "MultiplyResult", namespace = "http://tempuri.org/")
    public int getMultiplyResult() {
        return MultiplyResult;
    }

    public void setMultiplyResult(int MultiplyResult) {
        this.MultiplyResult = MultiplyResult;
    }
}
