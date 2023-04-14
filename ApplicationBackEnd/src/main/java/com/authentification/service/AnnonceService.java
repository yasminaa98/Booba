package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.AnnonceType;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils ;

    public Annonce getAnnonceById(Long id_annonce) throws NotFoundException {
        Optional<Annonce> annonceOptional = annonceRepository.findById(id_annonce);
        if (annonceOptional.isPresent()) {
            return annonceOptional.get();
        } else {
            throw new NotFoundException("Annonce with id " + id_annonce + " not found.");
        }
    }

    public List<Annonce> getAllAnnonce() {
        return annonceRepository.findAll();
    }

    public List<Annonce> getAnnonceByCategory(String category) {
        return annonceRepository.findByCategory(category);
    }

    public User getAnnonceOwner(Long id_annonce) throws NotFoundException {
        Optional<Annonce> annonceOptional = annonceRepository.findById(id_annonce);
        if (annonceOptional.isPresent()) {
            Annonce annonce = annonceOptional.get();
            return annonce.getUser();
        } else {
            throw new NotFoundException("Annonce with id " + id_annonce + " not found.");
        }
    }

    public List<Annonce> getAnnoncesForSale(String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return annonceRepository.findByUserAndType(user.get(), AnnonceType.FOR_SALE);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Annonce> getAnnoncesForExchange(String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return annonceRepository.findByUserAndType(user.get(), AnnonceType.FOR_EXCHANGE);
        } else {
            return Collections.emptyList();
        }
    }



    public ResponseEntity<MessageResponse> addAnnonce(Annonce annonce, String token) {

        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Annonce newAnnonce = new Annonce();
            newAnnonce.setName(annonce.getName());
            newAnnonce.setPicture(annonce.getPicture());
            newAnnonce.setPrice(annonce.getPrice());
            newAnnonce.setType(annonce.getType());
            newAnnonce.setState(annonce.getState());
            newAnnonce.setAgeChild(annonce.getAgeChild());
            newAnnonce.setAgeToy(annonce.getAgeToy());
            newAnnonce.setCategory(annonce.getCategory());
            newAnnonce.setDescription(annonce.getDescription());
            newAnnonce.setEstArchive(false);
            newAnnonce.setUser(user.get());

            annonceRepository.save(newAnnonce);
            return ResponseEntity.ok(new MessageResponse("Annonce added successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add annonce."));
    }

    public ResponseEntity<MessageResponse> modifyAnnonce(Long id_annonce , Annonce annonce) {
          Annonce annonceExistent = annonceRepository.findById(id_annonce).orElse(null) ;
        if ( annonceExistent == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Annonce Not found")) ;
        }
               annonceExistent.setName(annonce.getName());
               annonceExistent.setPicture(annonce.getPicture());
               annonceExistent.setPrice(annonce.getPrice());
               annonceExistent.setState(annonce.getState());
               annonceExistent.setAgeChild(annonce.getAgeChild());
               annonceExistent.setAgeToy(annonce.getAgeToy());
               annonceExistent.setCategory(annonce.getCategory());
               annonceExistent.setDescription(annonce.getDescription());
        try {
            annonceRepository.save(annonceExistent);
            return ResponseEntity.ok(new MessageResponse("Modified successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not modified"));
        }
    }

    public Annonce archiveAnnonce(Long id_annonce) {
        Annonce annonce = annonceRepository.findById(id_annonce).orElse(null) ;
        if (annonce == null) {
            return null ;
        }
        annonce.setEstArchive(true);
        return annonceRepository.save(annonce);
    }

}
