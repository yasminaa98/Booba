package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.Favorite;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUser(User user) ;
    Optional<Favorite> findByUserAndAnnonce(User user , Annonce annonce) ;
    @Query(value = "Select * FROM favorites WHERE user_id = ?", nativeQuery = true)
    List<Favorite> findByUserId(Long user_id);


}