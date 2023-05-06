package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.entities.Auction;
import com.authentification.entities.Exchange;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.ExchangeRepository;
import com.authentification.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ExchangeService {
    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ExchangeRepository exchangeRepository;

    public List<Map<String, Object>> getUserExchanges(String username) {
        List<Exchange> exchanges = exchangeRepository.findByReceiver(username);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            Map<String, Object> exchangeMap = new HashMap<>();
            exchangeMap.put("id", exchange.getId());
            exchangeMap.put("receiver", exchange.getReceiver());
            exchangeMap.put("id_receiver_annonce", exchange.getId_receiver_annonce());
            exchangeMap.put("sender",exchange.getSender());
            exchangeMap.put("id_sender_annonce", exchange.getId_sender_annonce());
            exchangeMap.put("status",exchange.getStatus());
            response.add(exchangeMap);
        }
        return response;
    }
    public List<Map<String, Object>> getSenderRequests(String username) {
        List<Exchange> exchanges = exchangeRepository.findBySender(username);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            Map<String, Object> exchangeMap = new HashMap<>();
            exchangeMap.put("id", exchange.getId());
            exchangeMap.put("receiver", exchange.getReceiver());
            exchangeMap.put("id_receiver_annonce", exchange.getId_receiver_annonce());
            exchangeMap.put("sender",exchange.getSender());
            exchangeMap.put("id_sender_annonce", exchange.getId_sender_annonce());
            exchangeMap.put("status",exchange.getStatus());
            response.add(exchangeMap);
        }
        return response;
    }
    public Exchange getExchangeById(long id) throws NotFoundException {
        Optional<Exchange> exchange = exchangeRepository.findById(id);
        if (exchange.isPresent()) {
            return exchange.get();
        } else {
            throw new NotFoundException("Exchange with id " + id + " not found.");
        }
    }

    public ResponseEntity<MessageResponse> addExchangeOffer(Exchange exchange, String token) throws IOException {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Exchange newExchange = new Exchange();
            newExchange.setSender(username);
            newExchange.setId_sender_annonce(exchange.getId_sender_annonce());
            newExchange.setId_receiver_annonce(exchange.getId_receiver_annonce());
            newExchange.setStatus("waiting");
            Optional<Annonce> annonceOptional = annonceRepository.findById(exchange.getId_receiver_annonce());
            newExchange.setReceiver(annonceOptional.get().getUser().getUsername());
            exchangeRepository.save(newExchange);
            return ResponseEntity.ok(new MessageResponse("Exchange added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add exchange."));
    }
    public ResponseEntity<MessageResponse> updateStatus(String token,Long id_exchange, String newStatus) {
        Exchange existentExchange = exchangeRepository.findById(id_exchange).orElse(null);
        if (existentExchange == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Exchange not found"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            existentExchange.setStatus(newStatus);
            exchangeRepository.save(existentExchange);
            return ResponseEntity.ok(new MessageResponse("this status is updated to " + newStatus));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to update status"));
    }
}
