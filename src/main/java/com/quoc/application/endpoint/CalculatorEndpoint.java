package com.quoc.application.endpoint;

import com.quoc.application.service.CalculatorService;
import com.quoc.soap_calculator.CalculatorRequest;
import com.quoc.soap_calculator.CalculatorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorEndpoint {

    @PayloadRoot(namespace = "http://quoc.com/soap-calculator", localPart = "calculatorRequest")
    @ResponsePayload
    public CalculatorResponse calculatorRequest(@RequestPayload CalculatorRequest req) {
       CalculatorResponse res = new CalculatorResponse();

       String m = req.getInput1();
       String n = req.getInput2();
       String op = req.getOperation();

       try {
           String result = String.valueOf(CalculatorService.performOperation(m, op, n));
           res.setResult(result);
           return res;
       } catch (Error e) {
           res.setResult(e.getMessage());
           return res;
       }
    }
}
