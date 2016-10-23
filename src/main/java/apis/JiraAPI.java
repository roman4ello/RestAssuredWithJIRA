package apis;


import utils.ApiUrlPaths;

/**
 * Created by ramzes on 10.10.2016.
 */
public class JiraAPI  extends MyRequestSender {
    MyRequestSender myRequestSender = new MyRequestSender();


    public  void createIssue(String body){
        myRequestSender
                .createRequest(body)
                .post(ApiUrlPaths.ISSUE.getUrl());
    }


    public  void deleteIssue(String issueIDorKEYforDELETE){
        myRequestSender
                    .delete(ApiUrlPaths.ISSUE.getUrl()+"/"+  issueIDorKEYforDELETE);
    }

    public  void addComment(String body, String issueIDorKEYforADDComment){
        myRequestSender
                .createRequest(body)
                .post(ApiUrlPaths.ISSUE.getUrl()+"/"+  issueIDorKEYforADDComment+"/comment");
    }
}//class 
