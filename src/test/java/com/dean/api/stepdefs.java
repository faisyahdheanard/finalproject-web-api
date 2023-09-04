package com.dean.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.prefs.Preferences;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

public class stepdefs {
    private RequestSpecification request;
    private Response response;
    private JSONObject requestBody;
    private String userId1;
    private String userId2;
    private String userId3;
    private String userId4;



    @Given("the request header is properly set up")
    public void theRequestHeaderIsProperlySetUp() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("app-id", "64d711fde683bee42a525714");
    }

    //WITH NO PARAM NEEDED
    @When("send a {string} request to the {string} endpoint")
    public void sendARequestToTheEndpoint(String method, String endpointName) {
        switch (method) {
            case "GET":
                if (endpointName.equals("list")) {
                    response = request.when().get(endpoint.dummyapi_list);
                } else if (endpointName.equals("tag")) {
                    response = request.when().get(endpoint.dummyapi_tag);
                }
                break;
            case "POST":
                response = request.when().post(endpoint.dummyapi_create);
                break;
        }
//        System.out.println(response.getBody().asString());
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("all response should be match with {string}")
    public void allResponseShouldBeMatchWith(String JSONSchema) {
        File JSONFile = utility.getJSONSchema(JSONSchema);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));

    }

    @And("the limit and the page should be set to their default values")
    public void theLimitAndThePageShouldBeSetToTheirDefaultValues() {
        response.then().assertThat().body("limit", Matchers.equalTo(20));
        response.then().assertThat().body("page", Matchers.equalTo(0));

    }

    @When("send a {string} request to the {string} endpoint with id {string}")
    public void sendARequestToTheEndpointWithId(String method, String endpointName, String userId) {
        Preferences prefs = Preferences.userRoot().node("com.dean.api");
        userId1 = prefs.get("userId1", null);
        userId2 = prefs.get("userId2", null);
        userId3 = prefs.get("userId3", null);
        userId4 = prefs.get("userId4", null);

        switch (method) {
            case "GET":
                response = request.when().get(endpoint.dummyapi_user + userId);
                break;
            case "DELETE":
                if(userId.equals("userId1")) {
                    response = request.when().delete(endpoint.dummyapi_user + userId1);
                } else if(userId.equals("userId2")) {
                    response = request.when().delete(endpoint.dummyapi_user + userId2);
                } else if(userId.equals("userId3")) {
                    response = request.when().delete(endpoint.dummyapi_user + userId3);
                } else if(userId.equals("userId4")) {
                    response = request.when().delete(endpoint.dummyapi_user + userId4);
                } else {
                    response = request.when().delete(endpoint.dummyapi_user + userId);
                }

                break;
            case "PUT":
                if("userId1".equals(userId)) {
                    response = request.when().put(endpoint.dummyapi_user + userId1);
                } else if("userId2".equals(userId)) {
                    response = request.when().put(endpoint.dummyapi_user + userId2);
                }
                break;
        }
//        System.out.println(response.getBody().asString());
    }

    @And("the response body should contain important user details")
    public void theResponseBodyShouldContainImportantUserDetails() {
        response.then().body("id", notNullValue());
        response.then().body("firstName", notNullValue());
        response.then().body("lastName", notNullValue());
        response.then().body("email", notNullValue());
        //IMPORTANT: ALL OF THESE FIELDS SHOULD BE FILLED, other than that are optional
    }

    @When("the request paramaters set to:")
    public void theRequestParamatersSetTo(Map<String, String> parameter) {
        String limit = parameter.get("limit");
        String page = parameter.get("page");
        request.params("limit", limit, "page", page);
    }

    @And("the response body should contain:")
    public void theResponseBodyShouldContain(Map<String, String> expectedValues) {
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            String actualValue = response.path(key).toString();
            assertThat(actualValue, equalTo(value));
        }
    }

    @And("the response body matches the {string} request body")
    public void theResponseBodyMatchesTheRequestBody(String fields) {
        if (fields.equals("complete fields")) {
            assertThat(response.jsonPath().get("title"), equalTo(requestBody.getString("title")));
            assertThat(response.jsonPath().get("firstName"), equalTo(requestBody.getString("firstName")));
            assertThat(response.jsonPath().get("lastName"), equalTo(requestBody.getString("lastName")));
            assertThat(response.jsonPath().get("picture"), equalTo(requestBody.getString("picture")));
            assertThat(response.jsonPath().get("gender"), equalTo(requestBody.getString("gender")));
            assertThat(response.jsonPath().get("email"), equalTo(requestBody.getString("email")));
            assertThat(response.jsonPath().get("dateOfBirth"), equalTo(requestBody.getString("dateOfBirth")));
            assertThat(response.jsonPath().get("phone"), equalTo(requestBody.getString("phone")));

            JSONObject requestBodyLocation = requestBody.getJSONObject("location");
            assertThat(response.jsonPath().get("location.country"), equalTo(requestBodyLocation.getString("country")));
            assertThat(response.jsonPath().get("location.city"), equalTo(requestBodyLocation.getString("city")));
            assertThat(response.jsonPath().get("location.street"), equalTo(requestBodyLocation.getString("street")));
            assertThat(response.jsonPath().get("location.timezone"), equalTo(requestBodyLocation.getString("timezone")));
            assertThat(response.jsonPath().get("location.state"), equalTo(requestBodyLocation.getString("state")));
        } else if (fields.equals("limited fields")) {
            assertThat(response.jsonPath().get("firstName"), equalTo(requestBody.getString("firstName")));
            assertThat(response.jsonPath().get("lastName"), equalTo(requestBody.getString("lastName")));
            assertThat(response.jsonPath().get("email"), equalTo(requestBody.getString("email")));
        }
    }

    @When("the request body is set to:")
    public void theRequestBodyIsSetTo(Map<String, String> body) {
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String email = body.get("email");

        JSONObject bodyObj = utility.userRequestLimitedBody(firstName, lastName, email);
        request.body(bodyObj.toString());
    }

    @And("the response body should contain information that {string} and {string}")
    public void theResponseBodyShouldContainInformation(String errorBody, String dataMessage) {
        response.then().assertThat()
                .body("error", Matchers.equalTo(errorBody))
                .body("data.firstName", Matchers.equalTo(dataMessage));
    }


    @When("the request body for {string} with {string} is prepared")
    public void theRequestBodyForWithIsAlreadySet(String method, String dataRequest) {
        switch(method) {
            case "POST":
                if(dataRequest.equals("complete fields")) {
                    requestBody = payload.getPostData();
                    request.body(requestBody.toString());
                } else if (dataRequest.equals("limited fields")) {
                    requestBody = payload.getPostLimitedData();
                    request.body(requestBody.toString());
                } else if (dataRequest.equals("invalid fields")) {
                    requestBody = payload.getPost_Invalid();
                    request.body(requestBody.toString());
                } break;
            case "PUT":
                if (dataRequest.equals("limited fields")) {
                     requestBody = payload.getPutLimitedData();
                     request.body(requestBody.toString());
                } else if (dataRequest.equals("complete fields")) {
                     requestBody = payload.getPutData();
                     request.body(requestBody.toString());
                } else if (dataRequest.equals("null fields")) {
                     requestBody = payload.getPutLimitedData_Null();
                     request.body(requestBody.toString());
                }
            case "POST/PUT":
                if (dataRequest.equals("<MIN CHAR")) {
                    requestBody = payload.getName_SubMIN();
                    request.body(requestBody.toString());
                } else if (dataRequest.equals(">MAX CHAR")) {
                    requestBody = payload.getName_SubMAX();
                    request.body(requestBody.toString());
                }
        }
    }

    @And("the response body {string} will be named as {string} and will be used for the next tests")
    public void theResponseBodyIdWillBeNamedAsAndWillBeUsedForTheNextTests(String key, String variableName) {
        Preferences prefs = Preferences.userRoot().node("com.dean.api");
        if (variableName.equals("userId1")) {
            userId1 = response.jsonPath().getString(key);
            prefs.put("userId1", userId1);
//            System.out.println(userId1);
        } else if (variableName.equals("userId2")) {
            userId2 = response.jsonPath().getString(key);
            prefs.put("userId2", userId2);
        } else if (variableName.equals("userId3")) {
            userId3 = response.jsonPath().getString(key);
            prefs.put("userId3", userId3);
        } else if (variableName.equals("userId4")) {
            userId4 = response.jsonPath().getString(key);
            prefs.put("userId4", userId4);
        }
    }

    @And("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        response.then().body("title", notNullValue());
        response.then().body("picture", notNullValue());
        response.then().body("gender", notNullValue());
        response.then().body("dateOfBirth", notNullValue());
        response.then().body("phone", notNullValue());
        response.then().body("location.country", notNullValue());
        response.then().body("location.city", notNullValue());
        response.then().body("location.street", notNullValue());
        response.then().body("location.timezone", notNullValue());
        response.then().body("location.state", notNullValue());
    }
}
