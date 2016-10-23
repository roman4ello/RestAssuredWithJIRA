import apis.JiraAPI;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.Test;
import tools.JiraJSONTools;
import tools.JiraOtherTools;
import utils.IssueTypes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Issue_new_version_For_dependency {

    //the Date for writting simple description
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date = new Date();

    //--------------------------------------------------------------------

    //codeOfIssueType  - one of IssueType enums
    private final IssueTypes codeOfIssueType = IssueTypes.TASK_CODE;
    private final Integer issueType = codeOfIssueType.getCode();

    //issueIDorKEYforDELETE - that will be delete
    private   String issueIDorKEYforDELETE = "";

    //issueIDorKEYforADDComment - Issue that should already exist
    private   String issueIDorKEYforADDComment = "";

    //Text for summary
    private final String summary_words = "This is " + codeOfIssueType.toString() + " This code was created in: " + dateFormat.format(date);

    //createIssue
    //after using this method wil be append CreatedKEY in file (issue's keys.txt)
    @Test
    public void createIssue() {
        // подготовка тестовых данных
        JiraJSONTools jiraJSONTools = new JiraJSONTools();
        String login_body = jiraJSONTools.generateJSONForCreateIssue(summary_words, issueType);

        // создание объекта
        JiraAPI jiraAPI = new JiraAPI();
        jiraAPI.createIssue(login_body);

        // проверка ответа от сервера
        try {
             issueIDorKEYforADDComment = JiraOtherTools.getKEYofCreatedIssue(jiraAPI.response);
            issueIDorKEYforDELETE = issueIDorKEYforADDComment;
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(jiraAPI.response.statusCode(), 201);
        assertTrue(jiraAPI.response.contentType().contains(ContentType.JSON.toString()));

    }

    //--------------------------------------------------------------------


    ///deleteIssue
    @Test(dependsOnMethods = {"createAddComment"})
    public void deleteIssue() {
        //Delete() from Jira APi does not consist from any body

        // создание объекта
        JiraAPI jiraAPI = new JiraAPI();
        jiraAPI.deleteIssue(issueIDorKEYforDELETE);

        // проверка ответа от сервера
        assertEquals(jiraAPI.response.statusCode(), 204);
        assertTrue(jiraAPI.response.contentType().contains(ContentType.JSON.toString()));

    }

    //deleteISSUES
    //
    @Test
    public void deleteIssues() throws IOException {
        //Delete() from Jira APi does not consist from any body

        // создание объекта
        JiraAPI jiraAPI = new JiraAPI();

        Vector<String> vectorIssueKeys =
                JiraOtherTools.readKeysOfIssuesInFile("issue's keys.txt");

        for (String vectorIssueKey : vectorIssueKeys) {
            jiraAPI.deleteIssue(vectorIssueKey);
            // проверка ответа от сервера
            System.out.println("Issue  - " + vectorIssueKey + "  was successfully DELETED");

        }


    }

    //--------------------------------------------------------------------

    //wordsForAddComment - words wich will be add to comments of issue
    private final String wordsForAddComment = "Default commentary was added/created in:" + dateFormat.format(date);


    //addComment
    @Test(dependsOnMethods = {"createIssue"})
    public void createAddComment() {

        // подготовка тестовых данных
        JiraJSONTools jiraJSONtools = new JiraJSONTools();
        String body = jiraJSONtools.generateJSONForAddComment(wordsForAddComment);

        // создание объекта
        JiraAPI jiraAPI = new JiraAPI();
        jiraAPI.addComment(body, issueIDorKEYforADDComment);

        // проверка ответа от сервера
        assertEquals(jiraAPI.response.statusCode(), 201);
        assertTrue(jiraAPI.response.contentType().contains(ContentType.JSON.toString()));
    }

    //--------------------------------------------------------------------




}//class
