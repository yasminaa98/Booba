package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import com.authentification.service.FavoriteService;
import com.authentification.service.AnnonceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AnnonceService annonceService;


    @GetMapping("/{id_user}/getAllFavorite")
    public List<Favorite> getAllFavorites(@PathVariable Long id_user) {
        return favoriteService.getAllFavorites(id_user);
    }

    @PostMapping("/{id_annonce}/add-to-favorites")
    public void addToFavorites(@PathVariable Long id_annonce, HttpSession session)throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.addToFavorites(annonce, session);
    }

    @DeleteMapping("/{id_annonce}/remove-from-favorites")
    public void removeFromFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.removeFromFavorites(user, annonce);
    }

}


