package com.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// Handler value: example.Handler
public class Handler implements RequestHandler<Object, String>{

    //Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LambdaLogger logger = null;

    @Override
    public String handleRequest(Object input, Context context){
        logger = context.getLogger();

        JsonObject data = input != null ? JsonParser.parseString(input.toString()).getAsJsonObject() : new JsonObject();

        String response = "";

        try {
            switch (data.get("processType").getAsString()) {
                case "searchProcess":
                    response = searchProcess();
                    break;
                case "bookingProcess":
                    response = bookingProcess();
                    break;
                case "updateProcess":
                    response = updateProcess();
                    break;
                case "cancelProcess":
                    response = cancelProcess();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.log("Error: Method=Handler, class=HomeController: " + e.getMessage());
        }
        
        // log execution details
        /*logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(data));
        logger.log("EVENT TYPE: " + input.getClass());*/
        return response.toString();
    }

    public String searchProcess(){
        JsonObject result = new JsonObject();
        try {
            result.addProperty("processType", "search");
            result.addProperty("status", "ok");
        } catch (Exception e) {
            logger.log("Error: Method=updateProcess, class=HomeController: " + e.getMessage());
        }
        return result.toString();
    }

    public String bookingProcess(){
        JsonObject result = new JsonObject();
        try {
            result.addProperty("processType", "booking");
            result.addProperty("status", "ok");
        } catch (Exception e) {
            logger.log("Error: Method=updateProcess, class=HomeController: " + e.getMessage());
        }
        return result.toString();
    }

    public String updateProcess(){
        JsonObject result = new JsonObject();
        try {
            result.addProperty("processType", "update");
            result.addProperty("status", "ok");
        } catch (Exception e) {
            logger.log("Error: Method=updateProcess, class=HomeController: " + e.getMessage());
        }
        return result.toString();
    }

    public String cancelProcess(){
        JsonObject result = new JsonObject();
        try {
            result.addProperty("processType", "cancel");
            result.addProperty("status", "ok");
        } catch (Exception e) {
            logger.log("Error: Method=updateProcess, class=HomeController: " + e.getMessage());
        }
        return result.toString();
    }
}