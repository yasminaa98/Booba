package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import com.authentification.service.FavoriteService;
import com.authentification.service.AnnonceService;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {
    private FavoriteService favoriteService;
    private AnnonceService annonceService;

    private FavoriteController(FavoriteService favoriteService , AnnonceService annonceService) {
        this.favoriteService = favoriteService;
        this.annonceService = annonceService;
    }

    @GetMapping
    public List<Favorite> getAllFavorites(@AuthenticationPrincipal User user) {
        return favoriteService.getAllFavorites(user);
    }

    @PostMapping("/id_annonce")
    public void addToFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.addToFavorites(user, annonce);
    }

    @DeleteMapping("/id_annonce")
    public void removeFromFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.removeFromFavorites(user, annonce);
    }

}


