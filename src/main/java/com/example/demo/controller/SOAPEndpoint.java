package com.example.demo.controller;

import com.concretepage.gs_ws.PostRequest;
import com.concretepage.gs_ws.PostResponse;
import com.example.demo.service.ShoppingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SOAPEndpoint {
    private static final String NAMESPACE_URI = "http://www.my.com";
    static final Logger LOGGER = LogManager.getLogger(SOAPEndpoint.class);
    private final ShoppingService shoppingService;

    @Autowired
    public SOAPEndpoint(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postRequest")
    @ResponsePayload
    public PostResponse getCountry(@RequestPayload PostRequest request) {
        PostResponse response = new PostResponse();
        try {
            LOGGER.info("getting request");
            shoppingService.processRequest(request);
            String message = "Покупка выполнена успешно!";
            response.setResponse(message);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Error : {}", ex.getMessage());
            response.setResponse(ex.getMessage());
        } catch (SQLGrammarException ex) {
            LOGGER.error("Error : {}", ex.getMessage());
            response.setResponse("Ошибка при сохранении в базу данных: " + ex.getMessage());
        } catch (Exception ex){
            LOGGER.error("Error : {}", ex.getMessage());
            response.setResponse("Внутренняя ошибка: "+ ex.getMessage());
        }
        return response;
    }
}