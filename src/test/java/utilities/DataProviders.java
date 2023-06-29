package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="SignInData")
	public String[][] dataProvider() throws IOException {
		
		String path= ".\\TestData\\UserData.xlsx";		//path definition of the Excel data file
		
		ExcelUtility excel=new ExcelUtility(path);
		
		int rowno=excel.getRowCount("User_Info");
		int colno=excel.getCellCount("User_Info", 1);
		
		String data[][]=new String[rowno][colno];
		
		for(int i=1; i<=rowno; i++)
		{
			for(int j=0; j<colno; j++) {
				data[i-1][j]=excel.getCellData("User_Info", i, j);
			}
		}
		
		return data;
	}

}
