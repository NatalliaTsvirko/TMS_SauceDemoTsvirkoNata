package tests;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;



public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(String.format("Test '%s' succeed", result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(String.format("Test '%s' failed", result.getName()));
        WebDriver driver = (WebDriver) (result.getTestContext().getAttribute("driver"));
        AllureUtils.attachScreenshot(driver);
    }



    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(String.format("Test '%s' skipped", result.getName()));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        context.getPassedTests();
        context.getFailedTests();
    }
}
