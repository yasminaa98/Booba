package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import com.authentification.service.AccountService;
import com.authentification.service.FavoriteService;
import com.authentification.service.AnnonceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private AccountService accountService ;

    private FavoriteController(FavoriteService favoriteService , AnnonceService annonceService , AccountService accountService) {
        this.favoriteService = favoriteService;
        this.annonceService = annonceService;
        this.accountService = accountService ;
    }

    @GetMapping
    public List<Favorite> getAllFavorites(@AuthenticationPrincipal User user) {
        return favoriteService.getAllFavorites(user);
    }

    @PostMapping("/{id_annonce}/add-to-favorites")
    public void addToFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.addToFavorites(user,annonce);
    }

    @DeleteMapping("/{id_annonce}/remove-from-favorites")
    public void removeFromFavorites(@AuthenticationPrincipal User user, @PathVariable Long id_annonce) throws NotFoundException {
        Annonce annonce = annonceService.getAnnonceById(id_annonce);
        favoriteService.removeFromFavorites(user, annonce);
    }

}


