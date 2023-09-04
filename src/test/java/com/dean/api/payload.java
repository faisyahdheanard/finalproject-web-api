package com.dean.api;

import org.json.JSONObject;


public class payload {
    public static JSONObject getPostData() {
        JSONObject bodyObj = utility.userRequestBody("dr", "faisyah", "dheana", "https://i.pinimg.com/736x/69/59/cb/6959cb92f7cdd4c25c04a0fc80b41f16.jpg",
                "female", "fdheana@example.com", "2002-05-09T00:00:00.000Z", "000000", "country", "city", "street", "timezone", "state");

        return bodyObj;
    }

    public static JSONObject getPostLimitedData() {
        JSONObject bodyObj = utility.userRequestLimitedBody("austin", "richard", "beerbongs@example.com");
        return bodyObj;
    }

    //
    public static JSONObject getName_SubMIN() {
        JSONObject bodyObj = utility.userRequestLimitedBody("a", "richard", "beerbongs@example.com");
        return bodyObj;
    }

    public static JSONObject getName_SubMAX() {
        JSONObject bodyObj = utility.userRequestLimitedBody("aVeryVeryVeryVeryVeryLongFirstName", "richard", "beerbongs@example.com");
        return bodyObj;
    }

    public static JSONObject getPutLimitedData() {
        JSONObject bodyObj = utility.userRequestLimitedBody("example", "changed", "fdheana@example.com");
        return bodyObj;
    }

    public static JSONObject getPutData() {
        JSONObject bodyObj = utility.userRequestBody("mr", "george", "kusunoki", "https://biographon.com/wp-content/uploads/2019/07/Joji-Miller.jpg",
                                                    "male", "beerbongs@gmail.com", "1992-09-18", "0099000", "country", "city", "street", "timezone", "state");

        return bodyObj;
    }

    public static JSONObject getPutLimitedData_Null() {
        JSONObject bodyObj = utility.userRequestLimitedBody("", "", "fdheana@example.com");

        return bodyObj;
    }

    public static JSONObject getPost_Invalid() {
        JSONObject bodyObj = utility.userRequestBody("drs", "andrew", "davilla", "https://upload.wikimedia.org/wikipedia/commons/4/49/A_black_image.jpg",
                                                    "male", "davila@example.com", "2000-06-26", "09999", "country", "city", "street", "timezone", "state");
        return bodyObj;
    }
}

