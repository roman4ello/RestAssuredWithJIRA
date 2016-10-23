package tools;

import org.json.simple.JSONObject;

/**
 * Created by ramzes on 10.10.2016.
 */
public class JiraJSONTools {

    private static String name = "br777roman";
    private static String password = "br777roman";


    public String generateJSONForLogin() {

        JSONObject login_passw_jsonObj = new JSONObject();
        login_passw_jsonObj.put("username", name);
        login_passw_jsonObj.put("password", password);

        return login_passw_jsonObj.toJSONString();
    }


    public final String generateJSONForCreateIssue(String summaryPar, Integer issueType_codePar) {

        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject issuetype = new JSONObject();
        JSONObject assignee = new JSONObject();
        JSONObject reporter = new JSONObject();
        JSONObject priority = new JSONObject();

        String summary = summaryPar;
        String description = "This is some description for --- "+summaryPar;

        project.put("id", "10315");

        issuetype.put("id", issueType_codePar);
        assignee.put("name", name);
        reporter.put("name", name);
        priority.put("id", "1");

        fields.put("project", project);
        fields.put("summary", summary);
        fields.put("description", description);
        fields.put("issuetype", issuetype);
        fields.put("assignee", assignee);
        fields.put("reporter", reporter);
        fields.put("priority", priority);

        issue.put("fields", fields);

        return issue.toJSONString();
    }

    public final String generateJSONForAddComment(String commentaryPar) {

        JSONObject issue = new JSONObject();
        issue.put("body", commentaryPar);

        return issue.toJSONString();
    }




}//class 
