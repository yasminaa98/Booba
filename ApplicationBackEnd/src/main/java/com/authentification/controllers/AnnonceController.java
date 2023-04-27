package com.authentification.controllers;

import com.authentification.entities.Annonce;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;


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
    public List<Map<String, Object>> getAllAnnonce() {
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
    @GetMapping("/getUserAnnonces")
    public List<Map<String, Object>> getUserAnnonces(
                                @RequestHeader(value = "Authorization") String token) throws NotFoundException {
        return annonceService.getUserAnnonces(token);
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
                                                          @RequestHeader(value = "Authorization") String token) throws IOException {
            return annonceService.addAnnonce(annonce, token);
    }
    @PutMapping("/{id}/modify")
    public ResponseEntity<MessageResponse> modifyAnnonce(@PathVariable("id") Long id,
                                                         @RequestBody Annonce annonce,
                                                         @RequestHeader(value = "Authorization") String token) {
        return annonceService.modifyAnnonce(id, annonce, token);
    }
    @PutMapping("/{id}/archive")
    public ResponseEntity<MessageResponse> archiveAnnonce(@PathVariable("id") Long id,
                                                          @RequestHeader(value = "Authorization") String token) {
        return annonceService.archiveAnnonce(id, token);
    }
}


