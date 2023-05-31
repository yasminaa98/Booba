package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.AnnonceType;
import com.authentification.entities.Auction;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
    List<Annonce> findByCategory(String category);
    List<Annonce> findByUserAndType(User user, AnnonceType type);

    List<Annonce> findByUser(Optional<User> user);
    Optional<Annonce> findByAuction(Optional<Auction> auction);
    Optional<Annonce> findByPicturePath(String picturePath);
}

