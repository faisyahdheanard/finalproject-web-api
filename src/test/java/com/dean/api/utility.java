package com.dean.api;

import org.json.JSONObject;

import java.io.File;

public class utility {

    //UTILITY JSONSCHEMA FILE
    public static File getJSONSchema(String JSONFile) {
        return new File("src/test/resources/api/JSONSchema/" + JSONFile);
    }


    //UTILITY REQUEST BODY
    public static JSONObject userRequestBody(String title, String firstName, String lastName, String picture, String gender, String email, String dateOfBirth, String phone, String country, String city, String street, String timezone, String state) {
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("title", title);
        bodyObj.put("firstName", firstName);
        bodyObj.put("lastName", lastName);
        bodyObj.put("picture", picture);
        bodyObj.put("gender", gender);
        bodyObj.put("email", email);
        bodyObj.put("dateOfBirth", dateOfBirth);
        bodyObj.put("phone", phone);

        JSONObject locationObj = new JSONObject();
        locationObj.put("country", country);
        locationObj.put("city", city);
        locationObj.put("street", street);
        locationObj.put("timezone", timezone);
        locationObj.put("state", state);

        bodyObj.put("location", locationObj);
        return bodyObj;
    }

    public static JSONObject userRequestLimitedBody(String firstName, String lastName, String email) {
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("firstName", firstName);
        bodyObj.put("lastName", lastName);
        bodyObj.put("email", email);
        return bodyObj;
    }
}
