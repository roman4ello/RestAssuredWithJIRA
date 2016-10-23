package utils;

/**
 * Created by ramzes on 10.10.2016.
 */
public enum IssueTypes {

    BUG_CODE(10004),
    TASK_CODE(10003),
    STORY_CODE(10002),
    EPIC_CODE(10001);


    private Integer code;

    IssueTypes(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

