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
        List<Exchange> exchanges = exchangeRepository.findByUsername(username);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            Map<String, Object> exchangeMap = new HashMap<>();
            exchangeMap.put("id_Exchange", exchange.getId_Exchange());
            exchangeMap.put("username", exchange.getUsername());
            exchangeMap.put("id_HisAnnonce", exchange.getId_HisAnnonce());
            exchangeMap.put("id_AnnonceToExchange", exchange.getId_AnnonceToExchange());
            exchangeMap.put("status",exchange.getStatus());
            response.add(exchangeMap);
        }
        return response;
    }

    public ResponseEntity<MessageResponse> addExchangeOffer(Exchange exchange, String token) throws IOException {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Exchange newExchange = new Exchange();
            newExchange.setUsername(exchange.getUsername());
            newExchange.setId_AnnonceToExchange(exchange.getId_AnnonceToExchange());
            newExchange.setId_HisAnnonce(exchange.getId_HisAnnonce());
            newExchange.setStatus(false);
            exchangeRepository.save(newExchange);
            return ResponseEntity.ok(new MessageResponse("Exchange added successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Failed to add exchange."));
    }
}
