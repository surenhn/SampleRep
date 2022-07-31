package listenerpackage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ReporterType;

public class SuiteListener implements IReporter {
	public static ExtentReports extent;
	public static ExtentTest logger;
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		//  extent.startReporter(ReporterType.DB, System.getProperty("user.dir") +"/test-output/STMExtentReport.db");

		// Iterate over every suite assigned for execution
		for (ISuite suite : suites) {

			String suiteName = suite.getName();
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();
				//   buildTestNodes(tc.getPassedTests(), LogStatus.PASS);
				//      buildTestNodes(tc.getFailedTests(), LogStatus.FAIL);
				//      buildTestNodes(tc.getSkippedTests(), LogStatus.SKIP);
				System.out.println(
						"Passed tests for suite '" + suiteName + "' is:" + tc.getPassedTests().getAllResults().size());
				/*System.out.println(
						"Passed tests'" + suiteName + "' is:" + tc.getName());*/
				System.out.println(
						"Failed tests for suite '" + suiteName + "' is:" + tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped tests for suite '" + suiteName + "' is:"
						+ tc.getSkippedTests().getAllResults().size());
				
			}
		}
		  for (String s : Reporter.getOutput()) {
			    extent.setTestRunnerOutput(s);
			  }
		 // extent.flush();
		  //extent.close();

	}
}
