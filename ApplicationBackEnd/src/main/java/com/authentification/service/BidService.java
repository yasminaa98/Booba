package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.Bid;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.AuctionRespository;
import com.authentification.repositories.BidRepository;
import com.authentification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class BidService {

    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private AuctionRespository auctionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<MessageResponse> addBid(Bid bid, Long id_auction, String token) throws IOException {

        Auction existentAuction = auctionRepository.findById(id_auction).orElse(null);
        if (existentAuction == null) {
            return ResponseEntity.badRequest().body(new MessageResponse(" bid not found"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Auction> auction = auctionRepository.findById(id_auction);

        if (user.isPresent()) {
            Bid newBid = new Bid();
            newBid.setNote(bid.getNote());
            newBid.setPrice_proposed(bid.getPrice_proposed());
            newBid.setUser(user.get());
            newBid.setAuction(auction.get());
            bidRepository.save(newBid);
            return ResponseEntity.ok(new MessageResponse("bid added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add bid."));
    }

    public ResponseEntity<MessageResponse> updatePrice(String newPrice, String token, Long id_bid) {
        Bid existentBid = bidRepository.findById(id_bid).orElse(null);
        if (existentBid == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Auction not found"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        Auction auction = existentBid.getAuction();
        if (user.isPresent()) {
            existentBid.setPrice_proposed(newPrice);
            bidRepository.save(existentBid);
            int initial = Integer.parseInt(auction.getInitial_price());
            int proposed = Integer.parseInt(existentBid.getPrice_proposed());
            if (initial <= proposed) {
                auction.setInitial_price(newPrice);
            }

            return ResponseEntity.ok(new MessageResponse("price updates"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to update price"));
    }

    public List<Map<String, Object>> getAuctionBids(String token, Long id_auction) {

        Auction auction = auctionRepository.findById(id_auction).orElse(null);
        if (auction == null) {
            return Collections.emptyList();
        }

        List<Bid> bids = bidRepository.findByAuction(auction);
        List<Map<String, Object>> response = new ArrayList<>();
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
        for (Bid bid : bids) {
            Map<String, Object> annonceMap = new HashMap<>();
            annonceMap.put("id", bid.getId_bid());
            annonceMap.put("note", bid.getNote());
            annonceMap.put("price proposed", bid.getPrice_proposed());
            annonceMap.put("auction_id", bid.getAuction().getId_auction());
            annonceMap.put("user_id", bid.getUser().getId_user());
            response.add(annonceMap);
        }}

        return response;
    }
}