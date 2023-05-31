package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.service.FavoriteService;
import com.authentification.service.AnnonceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private JwtUtils jwtUtils ;

    /***
     * Api for getting all the favorites of a user
     * @param id_user
     * @return
     */
    @GetMapping("/{id_user}/getAllFavorite")
    public List<Favorite> getAllFavorites(@PathVariable Long id_user) {
        return favoriteService.getAllFavorites(id_user);
    }

    @PostMapping("/{id_annonce}/add-to-favorites")
    public ResponseEntity<?> addToFavorites(@PathVariable Long id_annonce, HttpServletRequest request) throws NotFoundException {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtUtils.getUserIdFromToken(token);
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.addToFavorites(annonce, userId);
        return ResponseEntity.ok(new MessageResponse("Annonce added to favorites successfully!"));
    }


    /***
     * Api for removing an annonce from the user's favorites
     * @param user
     * @param id_annonce
     * @throws NotFoundException
     */
    @DeleteMapping("/{id_annonce}/remove-from-favorites")
    public void removeFromFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.removeFromFavorites(user, annonce);
    }

}


