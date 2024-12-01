package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle stl;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;

	}

	public int getCellCount(String sheetName, int rownum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int column) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(column);

		String data;
		try {

			DataFormatter format = new DataFormatter();

			data = format.formatCellValue(cell);
		} catch (Exception e) {

			data = "";
		}

		wb.close();
		fi.close();
		return data;
	}

	public void setCellData(String SheetName, int rownum, int column, String Data) throws IOException {

		File xlfile = new File(path);

		if (!xlfile.exists()) {

			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if (wb.getSheetIndex(SheetName) == -1)
			wb.createSheet(SheetName);
		ws = wb.getSheet(SheetName);
		
		if(ws.getRow(rownum)==null)
			ws.createRow(rownum);
		row=ws.getRow(rownum);
		
		cell=row.createCell(column);
		cell.setCellValue(Data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		fi.close();
		fo.close();

	}

	public void fillGreenColour(String xlsheet, int rownum, int column) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(column);

		stl = wb.createCellStyle();

		stl.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		stl.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(stl);

		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public void fillRedColour(String xlsheet, int rownum, int column) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(column);

		stl = wb.createCellStyle();

		stl.setFillForegroundColor(IndexedColors.RED.getIndex());
		stl.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(stl);

		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}
}
