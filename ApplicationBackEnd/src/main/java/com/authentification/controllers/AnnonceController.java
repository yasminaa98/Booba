package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService ;

    // Add new Annonce :

    @PostMapping("/add-annonce")
    public ResponseEntity<MessageResponse> addAnnonce (@RequestBody Annonce annonce, HttpSession session) {
        return annonceService.addAnnonce(annonce,session);
    }


    // Modify Annonce :

    @PutMapping("/{id_annonce}/modify-annonce")
    public ResponseEntity<MessageResponse> modifyAnnonce (@PathVariable("id_annonce") Long id_annonce ,
                                                          @RequestBody Annonce annonce, User user) {
        return annonceService.modifyAnnonce(id_annonce ,annonce,user) ;
    }


    // Archive Annonce :

    @PutMapping("/{id_annonce}/archive-annonce")
    public Annonce archiveAnnonce (@PathVariable("id_annonce") Long id_annonce) {
        return annonceService.archiveAnnonce(id_annonce) ;
    }

}
