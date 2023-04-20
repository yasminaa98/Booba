package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuctionRespository extends JpaRepository<Auction,Long> {
        List<Auction> findByName(String name);
        List<Auction> findByUser(User user);
        List<Auction> findByAnnonce(Annonce annonce);
    }

