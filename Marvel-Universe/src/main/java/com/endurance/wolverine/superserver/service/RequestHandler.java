package com.endurance.wolverine.superserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

/**
 * Created by kapish on 17/6/17.
 */

public class RequestHandler {

    public static HttpResponse<JsonNode> sendGetRequest(String url) throws UnirestException {

        HttpResponse<JsonNode> jsonResponse = Unirest.get(url)
                .asJson();
        return jsonResponse;
    }

    public static HttpResponse<JsonNode> sendPostRequest(String url, Object object) throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = objectMapper.writeValueAsString(object);
        System.out.println(jsonInString);
        HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(jsonInString)
                .asJson();


        return jsonResponse;
    }

    public static HttpResponse<JsonNode> sendPutRequest(String url, Object object) throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = objectMapper.writeValueAsString(object);
        System.out.println(jsonInString);


        HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(jsonInString)
                .asJson();


        return jsonResponse;
    }

    public static HttpResponse<JsonNode> sendDeleteRequest(String url, Object object) throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = objectMapper.writeValueAsString(object);

        HttpResponse<JsonNode> jsonResponse = Unirest.delete(url)
                .header("Content-Type", "application/json")
                .body(jsonInString)
                .asJson();


        return jsonResponse;
    }



}
