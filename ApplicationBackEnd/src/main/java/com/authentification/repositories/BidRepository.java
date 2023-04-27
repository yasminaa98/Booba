package com.authentification.repositories;

import com.authentification.entities.Auction;
import com.authentification.entities.Bid;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findByUserAndAuction(Optional<User> user,Auction auction);
    List<Bid> findByAuction(Auction auction);
    Bid findBidByUserAndAuction(Optional<User> user,Auction auction);

}