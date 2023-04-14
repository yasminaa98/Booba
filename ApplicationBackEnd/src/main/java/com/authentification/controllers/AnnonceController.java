package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.AnnonceType;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.UserRepository;
import com.authentification.service.AnnonceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private AnnonceRepository annonceRepository ;

    @Autowired
    private JwtUtils jwtUtils ;

    private User user ;

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

    @GetMapping("/for-sale")
    public List<Annonce> getAnnoncesForSale(@RequestHeader("Authorization") String token) {
      return annonceService.getAnnoncesForSale(token);
    }


    @GetMapping("/for-exchange")
    public List<Annonce> getAnnonceForExchange(@RequestHeader("Authorization") String token) {
       return annonceService.getAnnoncesForExchange(token);
    }




    @PostMapping("/add")
        public ResponseEntity<MessageResponse> addAnnonce(@RequestBody Annonce annonce,
                                                          @RequestHeader(value = "Authorization") String token) {
            return annonceService.addAnnonce(annonce, token);
    }




    /***
     * Api for modifying an existent annonce
     * @param id_annonce
     * @param annonce
     * @return
     */
    @PutMapping("/{id_annonce}/modify-annonce")
    public ResponseEntity<MessageResponse> modifyAnnonce (@PathVariable("id_annonce") Long id_annonce ,
                                                          @RequestBody Annonce annonce) {
        return annonceService.modifyAnnonce(id_annonce ,annonce) ;
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


