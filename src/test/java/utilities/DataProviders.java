package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name ="LoginData")

	public String[][] getData() throws IOException {

		String path = ".\\testData\\book1.xlsx";

		ExcelUtility util = new ExcelUtility(path);

		int totalrows = util.getRowCount("Sheet1");
		int totalcolumns = util.getCellCount("Sheet1",1);

		String[][] data = new String[totalrows][totalcolumns];

		for (int i = 1; i < totalrows; i++) {

			for (int j = 0; j < totalcolumns; j++) {

				data[i][j] = util.getCellData("Sheet1", i, j);
			}
		}
		
		return data;

	}

}
