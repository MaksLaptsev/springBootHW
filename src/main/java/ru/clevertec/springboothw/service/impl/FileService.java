package ru.clevertec.springboothw.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
public class FileService {
    private final String defaultPath = System.getProperty("user.dir").concat(File.separator).concat("logo")
            .concat(File.separator);
    private final String defaultImagePath = defaultPath.concat("default.jpg");
    public String uploadFileAnGetFileName(Long channelId, MultipartFile file) {

        if (!(file == null)){
            try {
                FileUtils.writeByteArrayToFile(new File(defaultPath+channelId+file.getOriginalFilename()),file.getBytes());
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            return channelId+file.getOriginalFilename();
        }else return "default.jpg";
    }

    public String getFileFromStorageBase64(String fileName){

        try{
            String p = fileName == null ? defaultImagePath : defaultPath.concat(fileName);
            byte[] fileContent = FileUtils.readFileToByteArray(new File(p));
            return Base64.getEncoder().encodeToString(fileContent);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
