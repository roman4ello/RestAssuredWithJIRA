package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import tools.JiraJSONTools;
import utils.ApiUrlPaths;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by ramzes on 10.10.2016.
 */
//Class who sends requests
public class MyRequestSender {
    public static String JSESSIONID = null;
    public final static ContentType CONTENT_TYPE = ContentType.JSON;
    public static RequestSpecification requestSpecification = null;
    public static Response response = null;


    public MyRequestSender() {
        login();
    }

    public void login() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";

        JiraJSONTools jiraJSONInstrument = new JiraJSONTools();
        String credentials = jiraJSONInstrument.generateJSONForLogin();

        createRequest(credentials)
                .post(ApiUrlPaths.LOGIN.getUrl());

        this.JSESSIONID = response.then().extract().path("session.value");
    }

    public MyRequestSender createRequest(String body) {
        this.createRequestSpecification()
                .addHeader("Content-Type", JiraAPI.CONTENT_TYPE.toString())
                .addHeader("Cookie", "JSESSIONID=" + MyRequestSender.JSESSIONID)
                .addBody(body);
        return this;
    }


    public MyRequestSender createRequestSpecification() {
        requestSpecification = given().
                when();
        return this;
    }

    // этот метод сможет добавлять столько угодно хедеров
    public MyRequestSender addHeader(String headerName, String headerValue) {
        requestSpecification.header(headerName, headerValue);
        return this;
    }

    public MyRequestSender addBody(String body) {
        requestSpecification.body(body);
        return this;
    }

    public MyRequestSender post(String uri) {
        response = requestSpecification.post(uri);
        return this;
    }

    public MyRequestSender get(String uri) {
        response = requestSpecification.get(uri);
        return this;
    }

    public MyRequestSender delete(String uri) {
        response = requestSpecification.delete(uri);
        return this;
    }
}//class
