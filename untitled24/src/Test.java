import static org.junit.Assert.*;


import junit.framework.Assert;

import java.util.ArrayList;

public class Test {
    @org.junit.Test
    public void testLoginWithValidCredentials(){
       ArrayList<Child> children = new ArrayList<>();
         Child child = new Child("Ali");
            children.add(child);
            LoginScreen loginScreen = new LoginScreen(children);
            loginScreen.setVisible(false);
            boolean test = loginScreen.checkStudentUsername("Ali");
            Assert.assertEquals(true,test);


    }

    @org.junit.Test
    public void testLoginWithInvalidCredentials() {
        ArrayList<Child> children = new ArrayList<>();
        Child child = new Child("Ali");
        children.add(child);
        LoginScreen loginScreen = new LoginScreen(children);
        loginScreen.setVisible(false);
        boolean test = loginScreen.checkStudentUsername("Veli");
        Assert.assertEquals(false, test);
    }

    @org.junit.Test
    public void testReportGenerator() {
        ArrayList<Child> children = new ArrayList<>();
        Child child = new Child("Ali");
        children.add(child);
        boolean test = ReportGenerator.generateReport(children);
        Assert.assertEquals(true, test);
    }

    @org.junit.Test
    public void testEmptyUsername() {
        ArrayList<Child> children = new ArrayList<>();
        Child child = new Child("Ali");
        children.add(child);
        LoginScreen loginScreen = new LoginScreen(children);
        loginScreen.setVisible(false);
        boolean test = loginScreen.checkStudentUsername("");
        Assert.assertEquals(false, test);
    }
}
