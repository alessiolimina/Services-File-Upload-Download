package it.develhope.services.Services.Upload.and.Download2.services;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("{fileRepositoryFolder}")
    private String fileRepositoryFolder;

    public String upload(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName= UUID.randomUUID().toString();
        String completeFileName = newFileName + "." + extension;
        File finalFolder = new File(fileRepositoryFolder);
        if(!finalFolder.exists()) throw new IOException("Folder doesn't exist");
        if(!finalFolder.isDirectory()) throw new IOException("Final folder isn't a directory");


        File finalDestination = new File(fileRepositoryFolder + "\\" + completeFileName);
        if(finalDestination.exists()) throw new IOException("File conflict");

        file.transferTo(finalDestination);
        return completeFileName;
    }

    public byte[] download(String fileName) throws IOException {
    File fileFromRepository = new File(fileRepositoryFolder + "\\" + fileName);
    if(!fileFromRepository.exists()) throw new IOException("File doesn't exist");
    return IOUtils.toByteArray(new FileInputStream(fileFromRepository));
    }

    @SneakyThrows
    public void remove(String fileName) {
        File fileFromRepository = new File(fileRepositoryFolder + "\\" + fileName);
        if(!fileFromRepository.exists()) return;
        boolean deleteResult =  fileFromRepository.delete();
        if(deleteResult == false) throw new Exception("Cannot delete file");
    }
}
