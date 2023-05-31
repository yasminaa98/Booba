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
@Table(name="Auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_auction;

    @Column(name = "name")
    private String name;

    @Column(name="initial_price")
    private String initial_price ;

    @Column(name = "description")
    private String description;

    @Column(name = "start_dateTime")
    private String start_dateTime;

    @Column(name = "end_dateTime")
    private String end_dateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annonce")
    private Annonce annonce;

    @JsonIgnore
    @OneToMany(mappedBy = "auction", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Bid> Bids;
}
