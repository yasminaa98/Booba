package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.Bid;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.AuctionRespository;
import com.authentification.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class AuctionService {
    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private AuctionRespository auctionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public List<Map<String, Object>> getAllAuction() {
        List<Auction> auctions = auctionRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Auction auction : auctions) {
            Map<String, Object> auctionMap = new HashMap<>();
            auctionMap.put("id", auction.getId_auction());
            auctionMap.put("name", auction.getName());
            auctionMap.put("initial_price", auction.getInitial_price());
            auctionMap.put("start_dateTime", auction.getStart_dateTime());
            auctionMap.put("end_dateTime", auction.getEnd_dateTime());
            auctionMap.put("description", auction.getDescription());
            auctionMap.put("user_id", auction.getUser().getId_user());
            auctionMap.put("id_annonce", auction.getAnnonce().getId_annonce());
            response.add(auctionMap);
        }
        return response;
    }

    public ResponseEntity<MessageResponse> addAuction(Auction auction, String token) throws IOException {

        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Auction newAuction = new Auction();
            newAuction.setName(auction.getName());
            newAuction.setInitial_price(auction.getInitial_price());
            newAuction.setStart_dateTime(auction.getStart_dateTime());
            newAuction.setEnd_dateTime(auction.getEnd_dateTime());
            newAuction.setDescription(auction.getDescription());
            newAuction.setUser(user.get());
            auctionRepository.save(newAuction);
            return ResponseEntity.ok(new MessageResponse("Auction added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add auction."));
    }
    public ResponseEntity<MessageResponse> checkExistentAuction(Long id, String token) throws IOException {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<Annonce> annonceToAuction = annonceRepository.findById(id);
            Optional<Auction> auctionExistent = auctionRepository.findByAnnonce(annonceToAuction);
            if (auctionExistent.isPresent()) {

                return ResponseEntity.ok(new MessageResponse("Auction existe"));

            }

        }
        return ResponseEntity.notFound().build();

    }

    public User getAuctionOwner(Long id_auction) throws NotFoundException {
        Optional<Auction> auctionExistent = auctionRepository.findById(id_auction);
        if (auctionExistent.isPresent()) {
            Auction auction = auctionExistent.get();
            return auction.getUser();
        } else {
            throw new NotFoundException("Annonce with id " + id_auction  + " not found.");
        }
    }


    public ResponseEntity<MessageResponse> addAuctionToAnnonce(Long id, Auction auction, String token) throws IOException {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<Annonce> annonceToAuction = annonceRepository.findById(id);
            Optional<Auction> auctionExistent=auctionRepository.findByAnnonce(annonceToAuction);
            if (annonceToAuction.isPresent() && !auctionExistent.isPresent()) {
                Auction newAuction = new Auction();
                newAuction.setName(auction.getName());
                newAuction.setInitial_price(auction.getInitial_price());
                newAuction.setStart_dateTime(auction.getStart_dateTime());
                newAuction.setEnd_dateTime(auction.getEnd_dateTime());
                newAuction.setDescription(auction.getDescription());
                newAuction.setUser(user.get());
                newAuction.setAnnonce(annonceToAuction.get());
                auctionRepository.save(newAuction);
                return ResponseEntity.ok(new MessageResponse("Auction added successfully!"));
            }

            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to add auction."));
        }
    public ResponseEntity<MessageResponse> updatePrice(String token,Long id_auction, String newPrice) {
        Auction existentAuction = auctionRepository.findById(id_auction).orElse(null);
        if (existentAuction == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Auction not found"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            existentAuction.setInitial_price(newPrice);
            auctionRepository.save(existentAuction);
            return ResponseEntity.ok(new MessageResponse("this price is provided by "+user.get().getId_user().toString()));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to update price"));
            }
    public ResponseEntity<MessageResponse> getAuctionPrice(String token,Long id_auction ) {
        Auction existentAuction = auctionRepository.findById(id_auction).orElse(null);
        if (existentAuction == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Auction not found"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
        String price=existentAuction.getInitial_price();

            return ResponseEntity.ok(new MessageResponse(price));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to get price"));
    }

    public List<Map<String, Object>> getUserAuctions(String token) {
        String Username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(Username);
        List<Auction> auctions = auctionRepository.findByUser(user);
        List<Map<String, Object>> response = new ArrayList<>();
        if (user.isPresent()) {
            for (Auction auction:auctions) {
                Map<String, Object> auctionMap = new HashMap<>();
                auctionMap.put("id", auction.getId_auction());
                auctionMap.put("name", auction.getName());
                auctionMap.put("initial_price", auction.getInitial_price());
                auctionMap.put("start_dateTime", auction.getStart_dateTime());
                auctionMap.put("end_dateTime", auction.getEnd_dateTime());
                auctionMap.put("description", auction.getDescription());
                auctionMap.put("id_annonce", auction.getAnnonce().getId_annonce());
                response.add(auctionMap);
            }}

        return response;
    }
    public ResponseEntity<MessageResponse> deleteAuction(Long id_auction) {
        Auction existentAuction = auctionRepository.findById(id_auction).orElse(null);

        if (existentAuction == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
        }

        try {
            auctionRepository.deleteById(id_auction);
            return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not deleted"));
        }
    }

        }



