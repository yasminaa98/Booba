package com.authentification.repositories;

import com.authentification.entities.Auction;
import com.authentification.entities.Bid;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findByUser(User user);
    List<Bid> findByAuction(Auction auction);

}