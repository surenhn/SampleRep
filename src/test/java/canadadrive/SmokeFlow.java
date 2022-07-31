package canadadrive;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listenerpackage.AnnotationTransformer;
import listenerpackage.Retry;
import mainpackage.MainClass;
import pageobjectpackage.CanadaDriveObjects;
@Listeners({listenerpackage.TestListenerClass.class})

public class SmokeFlow extends MainClass{

	@Test(dataProvider = "SampleData", priority = 1, enabled = true)
	//retryAnalyzer = Retry.class
	public void sampleFlow(String Province,String MakeModel,String RamOption,String WarrantyMonth) throws Exception {
		logger = extent.startTest("CanadaDrive Sample Flow");
		//throw new ArithmeticException("hello");
		CanadaDriveObjects obj=new CanadaDriveObjects(driver,logger);
		obj.sampleFlow(Province,MakeModel,RamOption,WarrantyMonth); 
	}
	
	@DataProvider(name="SampleData")
	public Object[][] sampledata() throws IOException{Object[][] data = csvData();
		return data;
	}
	
}
