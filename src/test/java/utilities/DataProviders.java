package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
	    String path = ".\\testData\\Opencart_LoginTestData.xlsx"; // Taking xl file from testData

	    ExcelUtility xlutil = new ExcelUtility(path); // Creating an object for XLUtility

	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = xlutil.getCellCount("Sheet1", 1);

	    String logindata[][] = new String[totalrows][totalcols]; // Created for two-dimensional array to store the data

	    for (int i = 1; i <= totalrows; i++) { // Read the data from xl storing in two-dimensional array
	        for (int j = 0; j < totalcols; j++) { // i is rows, j is columns
	            logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
	        }
	    }
	    return logindata; // Returning two-dimensional array
	}


}
