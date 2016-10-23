package tools;

import com.jayway.restassured.response.Response;

import java.io.*;
import java.util.Vector;

/**
 * Created by ramzes on 23.10.2016.
 */
public class JiraOtherTools {

    //this method() returns and SOUT  KEY of created Issue
    //and write it to file  = issue's keys.txt
    public final static String getKEYofCreatedIssue(Response response) throws IOException {
        String returnValue =
                response.getBody().prettyPrint().
                        split("key", 10)[1].
                        split("self")[0].trim().
                        replaceAll(" ", "").
                        replaceAll("\"", "").
                        replaceAll(":", "").
                        replaceAll(",", "");


        StringBuilder sb = new StringBuilder();
        BufferedReader bufRd = new BufferedReader(new FileReader("issue's keys.txt"));
        String tempStr = "";
        while ((tempStr = bufRd.readLine()) != null) {
            sb.append(tempStr + "\n");
        }
        sb.append(returnValue);
        BufferedWriter bufWr = new BufferedWriter(new FileWriter("issue's keys.txt"));
        bufWr.write(sb.toString());
        bufWr.flush();
        bufRd.close();
        bufWr.close();


        System.out.println("It was created KEY: " + returnValue);
        return returnValue;


    }

    public final static Vector<String> readKeysOfIssuesInFile(String fileName) throws IOException {
//        String fileName = "issue's keys.txt";
        BufferedReader bufRd = new BufferedReader(new FileReader(fileName));
        String tempStr = "";
        Vector<String> vectorOfIssueKeys = new Vector();
        while ((tempStr = bufRd.readLine()) != null) {
            vectorOfIssueKeys.add(tempStr);
        }
        bufRd.close();
        BufferedWriter bufWr = new BufferedWriter(new FileWriter(fileName));
        bufWr.write("");
        bufWr.flush();
        bufWr.close();
        return vectorOfIssueKeys;
    }
}//class 
