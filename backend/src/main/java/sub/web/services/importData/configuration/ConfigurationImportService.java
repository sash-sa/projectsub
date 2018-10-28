package sub.web.services.importData.configuration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ConfigurationImportService {
    public <X> X get(Integer typ) throws IOException, ClassNotFoundException;
    public <X> X save(X x,Integer typ) throws IOException;
    public void upload(MultipartFile multipartFile,Integer typ) throws IOException, ClassNotFoundException, InvalidFormatException;
    public void readDebt(MultipartFile file,DebtConfiguration debtConfiguration) throws IOException, InvalidFormatException;
}
