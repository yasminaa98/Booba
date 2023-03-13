package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUser(User user) ;
    Optional<Favorite> findByUserAndAnnonce(User user , Annonce annonce) ;


}