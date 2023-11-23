package com.amos.nodeupstream.exceptions.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URISyntaxException;

@ControllerAdvice
public class NodeExceptionHandler {


    @Autowired
    ObjectMapper objectMapper;

    public static final Logger LOGGER=
            LoggerFactory.getLogger(NodeExceptionHandler.class);

    @ExceptionHandler(WebClientResponseException.class)
    public ProblemDetail handleRuntimeException(WebClientResponseException ex) throws URISyntaxException, JsonProcessingException {
        return objectMapper.readValue(ex.getResponseBodyAsString(),ProblemDetail.class);
//        try{
//            return objectMapper.readValue(ex.getResponseBodyAsString(),ProblemDetail.class);
//        }
//        catch (Exception e) {
//            return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getResponseBodyAsString());
//        }
    }
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRTex(HttpServerErrorException ex) throws URISyntaxException, JsonProcessingException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getResponseBodyAsString());
        return problemDetail;
    }
}
