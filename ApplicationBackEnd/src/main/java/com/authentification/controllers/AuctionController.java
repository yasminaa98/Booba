package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.UserRepository;
import com.authentification.service.AnnonceService;
import com.authentification.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private AnnonceRepository annonceRepository ;
    @Autowired
    private JwtUtils jwtUtils ;
    private User user ;
    @GetMapping("/getAllAuctions")
    public List<Map<String, Object>> getAllAuction() {
        return auctionService.getAllAuction();
    }

    @GetMapping("/{id_auction}/getPrice")
    public ResponseEntity<MessageResponse> getAuctionPrice(@PathVariable("id_auction") Long id,
                                                     @RequestHeader(value = "Authorization") String token) {
        return auctionService.getAuctionPrice(token,id);
    }
    @PostMapping("/addAuction")
    public ResponseEntity<MessageResponse> addAnnonce(
            @RequestBody Auction auction,
                                                      @RequestHeader(value = "Authorization") String token) throws IOException {
        return auctionService.addAuction(auction,token);
    }
    @PostMapping("/{id_annonce}/addAuctionToAnnonce")
    public ResponseEntity<MessageResponse> addAuctionToAnnonce(@PathVariable("id_annonce") Long id,
                                                         @RequestBody Auction auction,
                                                         @RequestHeader(value = "Authorization") String token) throws IOException {
        return auctionService.addAuctionToAnnonce(id, auction, token);
    }
    @PutMapping("/{id_auction}/updatePrice")
    public ResponseEntity<MessageResponse> updateFirstName(@PathVariable("id_auction") Long id_auction,
                                                           @RequestParam("newPrice") String newPrice,
                                                           @RequestHeader(value = "Authorization") String token) throws IOException {
        return auctionService.updatePrice(token,id_auction, newPrice);
    }
}