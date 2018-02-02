package venkat.journey.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class DataDriven {
	public WebDriver driver;
	public static String getCellValue(String XLPATH,String sheet, int r, int c) {
		 String value=" "; 
		try { 
			
			Workbook wb=WorkbookFactory.create(new FileInputStream(XLPATH));
			Sheet sht=wb.getSheet(sheet); 
			Row row=sht.getRow(r); Cell cell=row.getCell(c);
			value=cell.toString(); 
		} catch(Exception e) { }
		 return value; 
		}
	public static int getRowCount(String XLPATH,String sheet)
	{
		int count=0;
		try
		{
			Workbook wb=WorkbookFactory.create(new FileInputStream(XLPATH));
			Sheet sht=wb.getSheet(sheet);
			count=sht.getLastRowNum();
		}
		catch(Exception e)
		{
			
		}
		return count;
	}
	

}
