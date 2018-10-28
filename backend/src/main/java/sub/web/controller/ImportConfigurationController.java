package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sub.web.services.importData.configuration.ConfigurationImportService;
import sub.web.services.importData.configuration.DebtConfiguration;

import java.io.IOException;

@Component
@RequestMapping(value = "/import")
public class ImportConfigurationController {
    @Autowired
    private ConfigurationImportService configurationService;

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity getImport() {
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestParam("rowNum") Integer rowNum,
                               @RequestParam("street") String street,
                               @RequestParam("nomer_kv") String nomer_kv,
                               @RequestParam("famili") String famili,
                               @RequestParam("ls") String ls,
                               @RequestParam("summa") String summa
    ) throws IOException {
        return new ResponseEntity(configurationService.save(new DebtConfiguration(rowNum, street, nomer_kv, famili, ls, summa), 1),HttpStatus.OK);

    }
}
