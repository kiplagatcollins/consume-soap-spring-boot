package com.coki.soapclient;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculatorService {

    public int multiply(int numa, int numb) throws IOException {
        // Configure SSL certificate validation
        // HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        CertificateUtils.disableCertificateValidation();


        // Create and send the SOAP request
        String soapRequest = createMultiplySoapRequest(numa, numb);
        String wsEndpoint = "http://www.dneonline.com/calculator.asmx";
        String soapAction = "http://tempuri.org/Multiply";
        System.out.println(soapRequest);
        String response = SoapClient.sendSoapRequest(wsEndpoint, soapRequest, soapAction);

        System.out.println(response);

        // Process the SOAP response
        MultiplyResponse multiplyCalculatorResponseBody = SoapXmlUnmarshaller.unmarshal(response, MultiplyResponse.class);
        if (multiplyCalculatorResponseBody != null) {
            return multiplyCalculatorResponseBody.getMultiplyResult();
        } else {
            throw new RuntimeException("Failed to process SOAP response.");
        }
    }

    public int divide(int numa, int numb) throws IOException {
        // Configure SSL certificate validation
        // HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        CertificateUtils.disableCertificateValidation();

        // Create and send the SOAP request
        String soapRequest = createDivideSoapRequest(numa, numb);
        String wsEndpoint = "http://www.dneonline.com/calculator.asmx";
        String soapAction = "http://tempuri.org/Divide";
        System.out.println(soapRequest);
        String response = SoapClient.sendSoapRequest(wsEndpoint, soapRequest, soapAction);
        System.out.println(response);
        // Process the SOAP response
        DivideResponse divideResponse = SoapXmlUnmarshaller.unmarshal(response, DivideResponse.class);
        if (divideResponse != null) {
            return divideResponse.getDivideResult();
        } else {
            throw new RuntimeException("Failed to process SOAP response.");
        }
    }


    private String createMultiplySoapRequest(int intA, int intB) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <Multiply xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>" + intA + "</intA>\n" +
                "      <intB>" + intB + "</intB>\n" +
                "    </Multiply>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }

    private String createDivideSoapRequest(int intA, int intB) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <Divide xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>" + intA + "</intA>\n" +
                "      <intB>" + intB + "</intB>\n" +
                "    </Divide>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }

}
