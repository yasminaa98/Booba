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
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    public List<Map<String, Object>> getAllAnnonce() {
        return annonceService.getAllAnnonce();
    }
    @GetMapping("/{id_auction}/getAnnonceByAuctionId")
    public Annonce getAnnonceByAuctionId(@PathVariable("id_auction") Long id_auction) throws NotFoundException {
        return annonceService.getAnnonceByAuctionId(id_auction);
    }
    @GetMapping("classpath")
    public ResponseEntity<byte[]> fromClasspathAsResEntity(@RequestParam("picture") String picture) throws IOException {

        ClassPathResource imageFile = new ClassPathResource("images/profiles/"+picture);

        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/{id_annonce}/getAnnonceById")
    public Annonce getAnnonceById(@PathVariable("id_annonce") Long id_annonce) throws NotFoundException {
        return annonceService.getAnnonceById(id_annonce);
    }
    @GetMapping("/{category}")
    public List<Annonce> getAnnonceByCategory(@PathVariable String category) {
        return annonceService.getAnnonceByCategory(category);
    }
    @GetMapping("/{id_annonce}/AnnonceOwner")
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
    @PostMapping(value="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<MessageResponse> addAnnonce(@ModelAttribute Annonce annonce,
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


