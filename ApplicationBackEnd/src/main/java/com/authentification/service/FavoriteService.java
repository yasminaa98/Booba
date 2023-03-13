package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private AnnonceRepository annonceRepository ;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getAllFavorites(User user) {
        return favoriteRepository.findByUser(user);
    }

    public Annonce getAnnonceById(Long id_annonce) {
        return annonceRepository.findById(id_annonce).orElse(null);
    }
    public void addToFavorites(User user, Annonce annonce) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndAnnonce(user, annonce);
        if (existingFavorite.isPresent()) {
            throw new IllegalStateException("This annonce is already in the user's favorites !");
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setAnnonce(annonce);
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(User user, Annonce annonce) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndAnnonce(user, annonce);
        if (existingFavorite.isEmpty()) {
            throw new IllegalStateException("This annonce is not in the user's favorites!");
        }
        favoriteRepository.delete(existingFavorite.get());
    }
}


