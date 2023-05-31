package com.authentification.controllers;

import com.authentification.entities.Annonce;
import com.authentification.entities.Exchange;
import com.authentification.entities.User;
import com.authentification.jwt.JwtUtils;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.AnnonceRepository;
import com.authentification.repositories.ExchangeRepository;
import com.authentification.repositories.UserRepository;
import com.authentification.service.AnnonceService;
import com.authentification.service.ExchangeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private AnnonceRepository annonceRepository ;
    @Autowired
    private JwtUtils jwtUtils ;
    private User user ;
    @Autowired
    private ExchangeRepository exchangeRepository;


    @GetMapping("/{reciever}/getUserExchanges")
    public List<Map<String, Object>> getUserExchanges(@PathVariable String reciever) {
        return exchangeService.getUserExchanges(reciever);
    }
    @GetMapping("/{sender}/getSenderRequests")
    public List<Map<String, Object>> getSenderRequests(@PathVariable String sender) {
        return exchangeService.getSenderRequests(sender);
    }
    @GetMapping("/{id}/getExchangeById")
    public Exchange getExchangeById(@PathVariable Long id) throws NotFoundException {
        return exchangeService.getExchangeById(id);
    }

    @PostMapping("/addExchangeOffer")
    public ResponseEntity<MessageResponse> addExchangeOffer(@RequestBody Exchange exchange,
                                                            @RequestHeader(value = "Authorization") String token) throws IOException {
        return exchangeService.addExchangeOffer(exchange,token);
    }

    @PutMapping("/{id_exchange}/updateStatus")
    public ResponseEntity<MessageResponse> updatePrice(@PathVariable("id_exchange") Long id_exchange,
                                                       @RequestParam("newStatus") String newStatus,
                                                       @RequestHeader(value = "Authorization") String token) throws IOException {
        return exchangeService.updateStatus(token,id_exchange, newStatus);
    }

}
