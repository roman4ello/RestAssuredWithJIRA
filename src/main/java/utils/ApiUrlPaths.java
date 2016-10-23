package utils;

/**
 * Created by ramzes on 10.10.2016.
 */
public enum ApiUrlPaths {

    LOGIN("/rest/auth/1/session"),
    ISSUE("/rest/api/2/issue"),
    SEARCH("/rest/api/2/search");

    private  String url;

    ApiUrlPaths(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}//class
