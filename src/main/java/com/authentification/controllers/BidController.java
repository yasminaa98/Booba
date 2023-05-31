package com.authentification.controllers;

import com.authentification.entities.Auction;
import com.authentification.entities.Bid;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.UserRepository;
import com.authentification.service.AuctionService;
import com.authentification.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bids")
public class BidController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private BidService bidService;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private AnnonceRepository annonceRepository ;
    @Autowired
    private JwtUtils jwtUtils ;
    private User user ;

    @GetMapping("{id_auction}/getBids")
    public List<Map<String, Object>> getAuctionBids(@PathVariable("id_auction") Long id,
                                                    @RequestHeader(value = "Authorization") String token) throws IOException {
        return bidService.getAuctionBids(token,id);
    }
    @GetMapping("{id_auction}/getUserBid")
    public Map<String, Object> getUserBid(@PathVariable("id_auction") Long id,
                                                    @RequestHeader(value = "Authorization") String token) throws IOException {
        return bidService.getUserBid(token,id);
    }
    @PostMapping("/{id_auction}/addBid")
    public ResponseEntity<MessageResponse> addBid(@RequestBody Bid bid,
                                                  @PathVariable("id_auction") Long id,
                                                  @RequestHeader(value = "Authorization") String token) throws IOException {
        return bidService.addBid(bid,id,token);
    }
    @PutMapping("/{id_auction}/update_price")
    public ResponseEntity<MessageResponse> updatePrice(
                                                 @PathVariable("id_auction") Long id,
                                                  @RequestParam("newPrice") String newPrice,
                                                  @RequestHeader(value = "Authorization") String token) {
        return bidService.updatePrice(newPrice,token,id);
    }
}
