package it.develhope.services.Services.Upload.and.Download2.dto;

import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import lombok.Data;

@Data
public class DownloadProfilePictureDTO {

    private Utente utente;
    private byte[] profileImage;
}
