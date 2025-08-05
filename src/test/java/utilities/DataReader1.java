package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader1 {
	
	public static List<HashMap<String,String>> data(String filepath,String sheetname) throws IOException
	{
		List<HashMap<String,String>> lhm=new ArrayList<>();
		FileInputStream file=new FileInputStream(filepath);
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet(sheetname);
		int totalrowcount=sheet.getLastRowNum();
		XSSFRow headerrow=sheet.getRow(0);
		
		for(int i=1;i<=totalrowcount-1;i++)
		{
			XSSFRow currentrow=sheet.getRow(i);
			HashMap<String,String> hm=new HashMap<String,String>();
			for(int j=0;j<=currentrow.getLastCellNum()-1;j++)
			{
				XSSFCell cell=currentrow.getCell(j);
				hm.put(headerrow.getCell(j).toString(),cell.toString());
			}
			lhm.add(hm);
		}
		file.close();
		return lhm;
	}

}
