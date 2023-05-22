package it.develhope.services.Services.Upload.and.Download2.controllers;

import it.develhope.services.Services.Upload.and.Download2.dto.DownloadProfilePictureDTO;
import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import it.develhope.services.Services.Upload.and.Download2.repositories.UserRepository;
import it.develhope.services.Services.Upload.and.Download2.services.FileStorageService;
import it.develhope.services.Services.Upload.and.Download2.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public Utente create(Utente utente){
     utente.setId(null);
     return userRepository.save(utente);
    }

    @SneakyThrows
    @PostMapping("/{id}/profile")
    public Utente uploadProfileImage(@PathVariable Long id, @RequestParam MultipartFile profilePicture) {
    return userService.uploadProfilePicture(id, profilePicture);
    }

    @GetMapping
    public List<Utente> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Utente> getOne(@PathVariable Long id){
      return userRepository.findById(id);

    }
    @SneakyThrows
    @GetMapping("/{id}/profile")
    public @ResponseBody byte[] getProfileImage(@PathVariable Long id, HttpServletResponse response){
    DownloadProfilePictureDTO downloadProfilePictureDTO = userService.downloadProfilePicture(id);
    String fileName = downloadProfilePictureDTO.getUtente().getProfilePicture();
    if(fileName == null) throw new Exception("User doesn't have a profile picture");
    String extension = FilenameUtils.getExtension(fileName);
        switch(extension){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case"jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        return downloadProfilePictureDTO.getProfileImage();
    }


    @PutMapping("/{id}")
    public void update(@RequestBody Utente utente, @PathVariable Long id){
        utente.setId(id);
        userRepository.save(utente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
