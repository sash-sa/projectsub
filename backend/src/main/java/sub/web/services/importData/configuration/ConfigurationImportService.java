package sub.web.services.importData.configuration;

import java.io.IOException;

public interface ConfigurationImportService {
    public <X> X get(Integer typ);
    public <X> X save(X x,Integer typ) throws IOException;
}
