package com.coki.soapclient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/multiply")
    public int multiply(@RequestBody CalculatorRequest calculatorRequest) throws IOException {
        return calculatorService.multiply(calculatorRequest.getNum1(), calculatorRequest.getNum2());
    }

    @PostMapping("/divide")
    public int divide(@RequestBody CalculatorRequest calculatorRequest) throws IOException {
        return calculatorService.divide(calculatorRequest.getNum1(), calculatorRequest.getNum2());
    }
}
