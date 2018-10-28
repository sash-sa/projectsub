package sub.web.controller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sub.web.services.importData.configuration.ConfigurationImportService;
import sub.web.services.importData.configuration.DebtConfiguration;

import javax.websocket.server.PathParam;
import java.io.IOException;

@Component
@RequestMapping(value = "/api/import")
public class ImportConfigurationController {
    @Autowired
    private ConfigurationImportService configurationService;

    @RequestMapping(method = RequestMethod.GET, value = "/{typ}")
    public ResponseEntity getImport(@PathVariable("typ") Integer typ) throws IOException, ClassNotFoundException {
        return new ResponseEntity((DebtConfiguration) configurationService.get(typ), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody DebtConfiguration debtConfiguration) throws IOException {
        return new ResponseEntity(configurationService.save(debtConfiguration, 1), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/{typ}")
    public ResponseEntity upload(OAuth2Authentication authentication, @RequestParam(value = "file", required = false) MultipartFile file,
                                 @PathVariable("typ") Integer typ) {
        try {
            configurationService.upload(file, typ);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
