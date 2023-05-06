package com.authentification.repositories;

import com.authentification.entities.Annonce;
import com.authentification.entities.Exchange;
import com.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository  extends JpaRepository<Exchange,Long> {
    List<Exchange> findBySender(String sender);
    List<Exchange> findByReceiver(String receiver);


}
