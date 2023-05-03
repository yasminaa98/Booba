package com.authentification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Exchange;

    @Column(name = "username")
    private String username;

    @Column(name="id_HisAnnonce")
    private Long id_HisAnnonce ;

    @Column(name = "id_AnnonceToExchange")
    private Long id_AnnonceToExchange;

    @Column(name = "status")
    private Boolean status;

}