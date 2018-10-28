package sub.test;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sub.db.entity.Debt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    @org.junit.Test
    public void testXls() throws IOException, InvalidFormatException {
        Integer rowNum = 5-1;
        String streetName = "B";
        String nomerKvName = "C";
        String familiName = "D";
        String lsName = "E";
        String summaName = "G";

        Integer streetNameIndex = CellReference.convertColStringToIndex(streetName);
        Integer nomerKvNameIndex = CellReference.convertColStringToIndex(nomerKvName);
        Integer familiNameIndex = CellReference.convertColStringToIndex(familiName);
        Integer lsNameIndex = CellReference.convertColStringToIndex(lsName);
        Integer summaNameIndex = CellReference.convertColStringToIndex(summaName);

        Workbook workbook = new XSSFWorkbook(new File("/home/vorobey/Рабочий стол/testXLS.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        List<Debt> data = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        int i = 0;
        while (true) {

            Row row = sheet.getRow(rowNum);
            if (row != null) {
                Debt debt = new Debt();
                debt.setFamili(getElement(row.getCell(familiNameIndex)));
                debt.setLs(getElement(row.getCell(lsNameIndex)));
                debt.setSumma(getElement(row.getCell(summaNameIndex)));
                debt.setNomerKv(getElement(row.getCell(nomerKvNameIndex)));
                debt.setStreet(getElement(row.getCell(streetNameIndex)));
                data.add(debt);
            } else break;
            rowNum++;
        }
        System.out.println(data);
    }

    String getElement(Cell cell) {
        try {
            if (cell != null) {
                DataFormatter formatter = new DataFormatter();
                return formatter.formatCellValue(cell);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
