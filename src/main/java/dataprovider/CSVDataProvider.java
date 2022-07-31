package dataprovider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {

	//private CSVReader reader=null;
	//private Object[][] data=null;
	
	 public static Object[][] getSearchData(String FileName) throws IOException{ 
         List<Object[]> records=new ArrayList<Object[]>(); 
         String record; 
          //Set the UTF-8 character set, use the character input stream BufferedReader with buffer to read the content of the file 
         BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(FileName),"UTF-8")); 
          //Ignore the header line of the CSV file (the first line) 
         file.readLine(); 
          // Traverse and read all the contents of the file except the first line and store them in an ArrayList named records. The object stored in each line of records is a String array 
         while((record=file.readLine())!=null){ 
             String fields[]=record.split(","); 
//           System.out.println(fields);
             records.add(fields); 
         } 
          //Close the file object 
         file.close(); 
          //Convert the List storing test data into a two-dimensional array of Object 
         Object[][] results=new Object[records.size()][]; 
          //Set the value of each row of the two-digit array, each row is an Object object 
         for(int i=0;i<records.size();i++){ 
             results[i]=records.get(i); 
         } 
         return results;      
     } 
}
