package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.service.AnnonceService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService ;

    @GetMapping("/getAll")
    public List<Annonce> getAllAnnonce() {
        return annonceService.getAllAnnonce();
    }

    @GetMapping("/{category}")
    public List<Annonce> getAnnonceByCategory(@PathVariable String category) {
        return annonceService.getAnnonceByCategory(category);
    }

    @GetMapping("/{id_annonce}/user")
    public User getUserByAnnonceId(@PathVariable("id_annonce") Long id_annonce) throws NotFoundException {

            return annonceService.getAnnonceOwner(id_annonce);

    }


    /***
     * Api for adding a new annonce
     * @param annonce
     * @param session
     * @return
     */
    @PostMapping("/add-annonce")
    public ResponseEntity<MessageResponse> addAnnonce (@RequestBody Annonce annonce, HttpSession session) throws NotFoundException {
        return annonceService.addAnnonce(annonce,session);
    }

    /***
     * Api for modifying an existent annonce
     * @param id_annonce
     * @param annonce
     * @param user
     * @return
     */
    @PutMapping("/{id_annonce}/modify-annonce")
    public ResponseEntity<MessageResponse> modifyAnnonce (@PathVariable("id_annonce") Long id_annonce ,
                                                          @RequestBody Annonce annonce, User user) {
        return annonceService.modifyAnnonce(id_annonce ,annonce,user) ;
    }

    /***
     * Api for archiving an annonce
     * @param id_annonce
     * @return
     */

    @PutMapping("/{id_annonce}/archive-annonce")
    public Annonce archiveAnnonce (@PathVariable("id_annonce") Long id_annonce) {
        return annonceService.archiveAnnonce(id_annonce) ;
    }




}


