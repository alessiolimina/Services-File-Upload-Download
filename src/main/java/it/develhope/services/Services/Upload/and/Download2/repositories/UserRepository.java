package it.develhope.services.Services.Upload.and.Download2.repositories;

import it.develhope.services.Services.Upload.and.Download2.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <Utente, Long> {
}
