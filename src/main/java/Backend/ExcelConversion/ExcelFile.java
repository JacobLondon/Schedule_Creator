package Backend.ExcelConversion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DataTypes.Location;
import DataTypes.Schedule;

public class ExcelFile extends XSSFWorkbook {
	
	private Sheet sheet;

	public ExcelFile(Schedule schedule){
		sheet = createSheet(WorkbookUtil.createSafeSheetName("Schedule"));
		for(int i = 0; i < schedule.numRows(); i++){
			Row currRow = sheet.createRow(i);
			for(int j = 0; j < schedule.numCols(); j++){
				Cell currCell = currRow.createCell(j);
				currCell.setCellValue(schedule.getText(i, j));
				CellStyle style = createCellStyle();
				style.setFillBackgroundColor(schedule.getBGColor(i,j));
				style.setFillForegroundColor(schedule.getFGColor(i,j));
				currCell.setCellStyle(style);
			}
		}
		
		Row legend = sheet.createRow(schedule.numRows() + 1);
		for(int i = 0; i < schedule.getNumLocations(); i++){
			Cell currCell = legend.createCell(i);
			Location currLocation = schedule.getLocations(i);
			currCell.setCellValue(currLocation.getName());
			CellStyle style = createCellStyle();
			style.setFillForegroundColor(new XSSFColor(currLocation.getLocationColor().getColor()).getIndex());
		}
	}
	
	public void saveAs(String name){
		try {
			FileOutputStream fileOut = new FileOutputStream(name);
			write(fileOut);
			fileOut.close();

		} catch (FileNotFoundException e) {
			System.err.println("no file found to save to");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO exception when saving file");
			e.printStackTrace();
		}
	}

}
