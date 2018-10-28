package sub.web.services.importData.configuration;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sub.db.entity.Debt;
import sub.db.entity.ImportConfiguration;
import sub.db.repo.ImportConfigurationRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class ConfigurationImportImportServiceImpl implements ConfigurationImportService {
    @Autowired
    private ImportConfigurationRepository importConfigurationRepository;

    @Override
    public <X> X get(Integer typ) throws IOException, ClassNotFoundException {
        DebtConfiguration debtConfiguration=null;
        switch (typ) {
            case 1: {
                Optional<ImportConfiguration> importConfiguration= importConfigurationRepository.findById("debt");
                if(importConfiguration.isPresent()){
                    ObjectInputStream inputStream=new ObjectInputStream(new ByteArrayInputStream(importConfiguration.get().getData()));
                    debtConfiguration=(DebtConfiguration)inputStream.readObject();
                }
                else debtConfiguration=new DebtConfiguration();
                break;
            }
        }
        return (X)debtConfiguration;
    }

    @Override
    public <X> X save(X x, Integer typ) throws IOException {
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        switch (typ) {
            case 1: {
                DebtConfiguration debtConfiguration = (DebtConfiguration) x;
                objectOutputStream.writeObject(debtConfiguration);
                return (X)importConfigurationRepository.save(new ImportConfiguration("debt",outputStream.toByteArray()));
            }
        }
        return x;
    }

    @Override
    public void upload(MultipartFile multipartFile, Integer typ) throws IOException, ClassNotFoundException, InvalidFormatException {
        switch (typ){
            case 1:{
                readDebt(multipartFile,(DebtConfiguration) get(1));
                break;
            }
        }
    }

    @Override
    public void readDebt(MultipartFile file,DebtConfiguration debtConfiguration) throws IOException, InvalidFormatException {
        if(file!=null) {
            Integer rowNum = debtConfiguration.getRowNum()-1;
            Integer streetNameIndex = convertColStringToIndex(debtConfiguration.getStreetName());
            Integer nomerKvNameIndex = convertColStringToIndex(debtConfiguration.getNomerKvName());
            Integer familiNameIndex = convertColStringToIndex(debtConfiguration.getFamiliName());
            Integer lsNameIndex = convertColStringToIndex(debtConfiguration.getLsName());
            Integer summaNameIndex = convertColStringToIndex(debtConfiguration.getSummaName());

            Workbook workbook = new XSSFWorkbook(file.getInputStream());
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
    }

    Integer convertColStringToIndex(String name){
        try{
            return CellReference.convertColStringToIndex(name);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
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
