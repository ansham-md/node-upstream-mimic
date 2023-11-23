package com.amos.nodeupstream.controller;

import com.amos.nodeupstream.model.DPRequest;
import com.amos.nodeupstream.web.HttpExchangeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/domestic-payments-channel-api")
public class nodeController {

    public static final Logger LOGGER=
            LoggerFactory.getLogger(nodeController.class);

    @Autowired
    private HttpExchangeHandler httpExchangeHandler;

    @PostMapping
    public DPRequest addConsents(@RequestBody DPRequest dpRequest) {
        LOGGER.info("Sending data to Downstream to create new consent: {} ", dpRequest);
        return httpExchangeHandler.addConsents(dpRequest);
    }

    @GetMapping
    public List<DPRequest> findAll() {
        LOGGER.info("Sending request to Downstream to fetch all data {}");
        return httpExchangeHandler.findAll();
    }

    @GetMapping("/{consentId}")
    public DPRequest findById(@PathVariable Long consentId) {
        LOGGER.info("Downstream call to get data for consentid: " + consentId);
        return httpExchangeHandler.findById(consentId);
    }

    @PutMapping("/{consentId}/status")
    public DPRequest updatePayment(@PathVariable Long consentId){
        DPRequest dpRequest=httpExchangeHandler.findById(consentId);
        dpRequest.setTppName(dpRequest.getTppName()+" -PaymentCompleted");
        return httpExchangeHandler.addConsents(dpRequest);
    }
}
