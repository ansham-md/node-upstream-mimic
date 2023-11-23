package com.amos.nodeupstream.web;

import com.amos.nodeupstream.model.DPRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface HttpExchangeHandler {

    @PostExchange("/domestic-payments")
    public DPRequest addConsents(@RequestBody DPRequest dpRequest);

    @GetExchange("/domestic-payments")
    public List<DPRequest> findAll();

    @GetExchange("/domestic-payments/{consentId}")
    public DPRequest findById(@PathVariable Long consentId);


}
