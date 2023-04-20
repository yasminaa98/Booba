package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.AuctionRespository;
import com.authentification.repositories.UserRepository;
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
            auctionMap.put("price", auction.getInitial_price());
            auctionMap.put("start_datetime", auction.getStart_dateTime());
            auctionMap.put("end_date_time", auction.getEnd_dateTime());
            auctionMap.put("description", auction.getDescription());
            auctionMap.put("user_id", auction.getUser().getId_user());
            auctionMap.put("annonce_id", auction.getAnnonce().getId_annonce());
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

            if (auction.getPicture() != null) {
                String fileName = auction.getPicture().getOriginalFilename();
                Path path = Paths.get("C:/AnnoncePictures/" + fileName);
                Files.write(path, auction.getPicture().getBytes());
                newAuction.setPicturePath(path.toString());
            }

            auctionRepository.save(newAuction);
            return ResponseEntity.ok(new MessageResponse("Auction added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add auction."));
    }

    public ResponseEntity<MessageResponse> addAuctionToAnnonce(Long id, Auction auction, String token) throws IOException {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Optional<Annonce> annonceToAuction = annonceRepository.findById(id);
            if (annonceToAuction.isPresent()) {
                Auction newAuction = new Auction();
                newAuction.setName(auction.getName());
                newAuction.setInitial_price(auction.getInitial_price());
                newAuction.setStart_dateTime(auction.getStart_dateTime());
                newAuction.setEnd_dateTime(auction.getEnd_dateTime());
                newAuction.setDescription(auction.getDescription());
                newAuction.setUser(user.get());
                newAuction.setAnnonce(annonceToAuction.get());

                if (auction.getPicture() != null) {
                    String fileName = auction.getPicture().getOriginalFilename();
                    Path path = Paths.get("C:/AnnoncePictures/" + fileName);
                    Files.write(path, auction.getPicture().getBytes());
                    newAuction.setPicturePath(path.toString());
                }
                auctionRepository.save(newAuction);
                return ResponseEntity.ok(new MessageResponse("Auction added successfully!"));
            }

            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to modify annonce."));
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

        }



