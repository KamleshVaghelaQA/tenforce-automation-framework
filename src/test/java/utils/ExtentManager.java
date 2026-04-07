package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {

        String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Tenforce Automation Report");
        sparkReporter.config().setDocumentTitle("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester", "QA Automation");
        extent.setSystemInfo("Framework", "Selenium + Cucumber + POM");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        return extent;
    }
}