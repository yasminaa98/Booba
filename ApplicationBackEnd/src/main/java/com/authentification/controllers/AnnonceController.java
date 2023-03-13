package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService ;

    // Add new Annonce :

    @PostMapping("/addannonce")
    public Annonce addAnnonce (@RequestParam  String name, String category,
                               String state, String ageChild, String ageToy, String description) {
        return annonceService.addAnnonce( name, category, state, ageChild, ageToy, description) ;

    }

    // Modify Annonce :

    @PutMapping("/modifyannonce")
    public Annonce modifyAnnonce (@PathVariable("id_annonce") Long id_annonce ,
                                  @RequestParam String name, String category,
                                  String state, String ageChild, String ageToy, String description) {
        return annonceService.modifyAnnonce(id_annonce, name, category, state, ageChild, ageToy, description) ;
    }


    // Archive Annonce :

    @PutMapping("/archiveannonce")
    public Annonce archiveAnnonce (@PathVariable("id_annonce") Long id_annonce) {
        return annonceService.archiveAnnonce(id_annonce) ;
    }

}
