package com.authentification.service;

import com.authentification.entities.*;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils ;

    public List<Map<String, Object>> getAllAnnonce() {
        List<Annonce> annonces = annonceRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Annonce annonce : annonces) {
            Map<String, Object> annonceMap = new HashMap<>();
            annonceMap.put("id", annonce.getId_annonce());
            annonceMap.put("name", annonce.getName());
            annonceMap.put("price", annonce.getPrice());
            annonceMap.put("type", annonce.getType());
            annonceMap.put("state", annonce.getState());
            annonceMap.put("ageChild", annonce.getAgeChild());
            annonceMap.put("ageToy", annonce.getAgeToy());
            annonceMap.put("category", annonce.getCategory());
            annonceMap.put("description", annonce.getDescription());
            annonceMap.put("picture",annonce.getPicturePath());
            annonceMap.put("user_id", annonce.getUser().getId_user());
            response.add(annonceMap);
        }

        return response;
    }

    public Annonce getAnnonceById(Long id_annonce) throws NotFoundException {
        Optional<Annonce> annonceOptional = annonceRepository.findById(id_annonce);
        if (annonceOptional.isPresent()) {
            return annonceOptional.get();
        } else {
            throw new NotFoundException("Annonce with id " + id_annonce + " not found.");
        }
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



    public ResponseEntity<MessageResponse> addAnnonce(Annonce annonce, String token) throws IOException {

        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Annonce newAnnonce = new Annonce();
            newAnnonce.setName(annonce.getName());
            newAnnonce.setPrice(annonce.getPrice());
            newAnnonce.setType(annonce.getType());
            newAnnonce.setState(annonce.getState());
            newAnnonce.setAgeChild(annonce.getAgeChild());
            newAnnonce.setAgeToy(annonce.getAgeToy());
            newAnnonce.setCategory(annonce.getCategory());
            newAnnonce.setDescription(annonce.getDescription());
            newAnnonce.setPicturePath(annonce.getPicturePath());
            newAnnonce.setEstArchive(false);
            newAnnonce.setUser(user.get());


            annonceRepository.save(newAnnonce);
            return ResponseEntity.ok(new MessageResponse("Annonce added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add annonce."));
    }

    public ResponseEntity<MessageResponse> modifyAnnonce(Long id, Annonce annonce, String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<Annonce> annonceToUpdate = annonceRepository.findById(id);
            if (annonceToUpdate.isPresent()) {
                Annonce updatedAnnonce = annonceToUpdate.get();
                if (updatedAnnonce.getUser().getId_user().equals(user.get().getId_user())) {
                    updatedAnnonce.setName(annonce.getName());
                    updatedAnnonce.setPrice(annonce.getPrice());
                    updatedAnnonce.setType(annonce.getType());
                    updatedAnnonce.setState(annonce.getState());
                    updatedAnnonce.setAgeChild(annonce.getAgeChild());
                    updatedAnnonce.setAgeToy(annonce.getAgeToy());
                    updatedAnnonce.setCategory(annonce.getCategory());
                    updatedAnnonce.setDescription(annonce.getDescription());
                    updatedAnnonce.setPicturePath(annonce.getPicturePath());
                    annonceRepository.save(updatedAnnonce);
                    return ResponseEntity.ok(new MessageResponse("Annonce modified successfully!"));
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("You are not allowed to modify this annonce."));
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify annonce."));
    }

    public ResponseEntity<MessageResponse> archiveAnnonce(Long id, String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<Annonce> annonceToArchive = annonceRepository.findById(id);
            if (annonceToArchive.isPresent()) {
                Annonce archivedAnnonce = annonceToArchive.get();
                if (archivedAnnonce.getUser().getId_user().equals(user.get().getId_user())) {
                    archivedAnnonce.setEstArchive(true);
                    annonceRepository.save(archivedAnnonce);
                    return ResponseEntity.ok(new MessageResponse("Annonce archived successfully!"));
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("You are not allowed to archive this annonce."));
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to archive annonce."));
    }
    public List<Map<String, Object>> getUserAnnonces(String token) {
        String Username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(Username);
        List<Annonce> annonces = annonceRepository.findByUser(user);
        List<Map<String, Object>> response = new ArrayList<>();
        if (user.isPresent()) {
            for (Annonce annonce:annonces) {
                Map<String, Object> annonceMap = new HashMap<>();
                annonceMap.put("id", annonce.getId_annonce());
                annonceMap.put("name", annonce.getName());
                annonceMap.put("price", annonce.getPrice());
                annonceMap.put("type", annonce.getType());
                annonceMap.put("state", annonce.getState());
                annonceMap.put("ageChild", annonce.getAgeChild());
                annonceMap.put("ageToy", annonce.getAgeToy());
                annonceMap.put("category", annonce.getCategory());
                annonceMap.put("description", annonce.getDescription());
                annonceMap.put("picture",annonce.getPicturePath());
                response.add(annonceMap);
            }}
        return response;
    }


}


