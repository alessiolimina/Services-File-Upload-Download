package it.develhope.services.Services.Upload.and.Download2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {

    @Value("{fileRepositoryFolder}")
    private String fileRepositoryFolder;



}
