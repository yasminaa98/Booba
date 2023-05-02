package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRespository extends JpaRepository<Auction,Long> {
        List<Auction> findByName(String name);
        List<Auction> findByUser(Optional<User> user);
        Optional<Auction> findByAnnonce(Optional<Annonce> annonce);
    }

