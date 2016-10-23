import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ramzes on 26.09.2016.
 */
public class MyIssue_old_version {
    String sessionId = "";
    String issueKey = "QAAUT-460";
    String issueId = "13445";
    String commentary = "first_comment";
    String issue_type = "10004";
//idPar = QAAUT-231


    @BeforeTest
    public void login() {
        String body = "{\n" +
                "    \"username\": \"br777roman\",\n" +
                "    \"password\": \"br777roman\"\n" +
                "}";
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        sessionId = given().
                contentType("application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().path("session.value");

        System.out.println("sessionId = " + sessionId);

    }

    @Test
    public void loginPositive() {
        String body = "{\n" +
                "    \"username\": \"br777roman\",\n" +
                "    \"password\": \"br777roman\"\n" +
                "}";
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        sessionId = given().
                contentType("application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().path("session.value");

        System.out.println("sessionId = " + sessionId);

    }

    @Test
    public void loginNegative() {
        String body = "{\n" +
                "    \"username\": \"br777roman\",\n" +
                "    \"password\": \"br777roman\"\n" +
                "}";
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        sessionId = given().
                contentType("application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(400).
                extract().path("session.value");

        System.out.println("sessionId = " + sessionId);

    }


    @Test
    public void createBug() {

        String keyNewIssue = "";
        //login();
        System.out.println("session ID = " + sessionId);

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080/";
        String body = "{\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"Create Bug by boyar\",\n" +
                "\t\t\"description\": \"create bug\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"name\": \"Bug\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"br777roman\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"br777roman\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        keyNewIssue = given().
                contentType("application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(body).
                when().
                post("/rest/api/2/issue").
                then().
                extract().
                response().asString();

        System.out.println("key new issue = " + keyNewIssue);

    }

    @Test
    public void deleteBug() {

        String keyNewIssue = "";
        //login();
        System.out.println("session ID = " + sessionId);

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080/";

        keyNewIssue = given().
                contentType("application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                when().
                delete("/rest/api/2/issue/" + issueKey).
                then().
                statusCode(204).
                extract().
                response().asString();

        System.out.println("key new issue = " + keyNewIssue);

    }

    @Test
    public void addCommentary() {

        String keyNewIssue = "";

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080/";
        String body = "{\n" +
                "    \"body\": \"this comment\"" +
                "\n" +
                "}";

        keyNewIssue = given().
                contentType("application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(body).
                when().
                post("/rest/api/2/issue/" + issueKey + "/comment").
                then().
                statusCode(201).
                extract().
                response().asString();

        System.out.println("key new issue = " + keyNewIssue);

    }


    @Test
    public void changeTypeIssue() {

        String keyNewIssue = "";
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080/";

        String body = "{\n" +
                "    \"name\": \"name\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"avatarId\": 1\n" +
                "}";

        keyNewIssue = given().
                contentType("application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(body).
                when().

        put("/rest/api/2/issuetype/" + issueKey).
                then().
                statusCode(204).
                extract().
                response().asString();

        System.out.println("key new issue = " + keyNewIssue);

    }

    @Test
    public void ChangeTypeOfIssue() {
        given()
                .contentType("application/json")
                .cookie("JSESSIONID=" + sessionId)
                .body("{\"fields\": \t{\"issuetype\": {\"id\": \"" + issue_type + "\"}}}")
                .put("/rest/api/2/issue/" + issueId)
                .then()
                .assertThat()
                .statusCode(204);
    }


    @Test
    public void getCommentary() {

        String keyNewIssue = "";
        //login();
        System.out.println("session ID = " + sessionId);

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080/";
        String body = "{\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"Create Bug by boyar\",\n" +
                "\t\t\"description\": \"create bug\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"name\": \"Bug\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"br777roman\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"br777roman\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        keyNewIssue = given().
                contentType("application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(body).
                when().
                get("/rest/api/2/issue/" + issueKey + "/comment").
                then().
                statusCode(200).
                extract().
                response().asString();

        System.out.println("key new issue = " + keyNewIssue);

    }


}//class



