package Javatest;

import org.testng.annotations.Test;

public class Topic_10_SetterGetter {
    private String fullName;
@Test
    public void SetterGetter(){
        setFullName("Automation Testing");
        System.out.println(getFullName());

    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
