package it.develhope.services.Services.Upload.and.Download2.controllers;

import it.develhope.services.Services.Upload.and.Download2.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/download")
    public void download(){

    }

    @PostMapping("/upload")
    public void upload(){

    }
}
