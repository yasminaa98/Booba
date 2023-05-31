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
@Table(name="Annonce")
public class Annonce {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_annonce;

        @Column(name = "name")
        private String name;

        @Column(name="price")
        private String price ;

        @Column(name = "state")
        private String state;

        @Enumerated(EnumType.STRING)
        private AnnonceType type;

        @Column(name = "picture")
        private String picturePath ;
        @Transient
        private MultipartFile picture;

        @Column(name = "ageChild")
        private String ageChild;

        @Column(name = "ageToy")
        private String ageToy;

        @Column(name = "category")
        private String category;

        @Column(name = "description")
        private String description;

        private boolean estArchive;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_user")
        private User user;
        @JsonIgnore
        @OneToMany(mappedBy = "annonce", cascade = CascadeType.REMOVE)
        private List<Auction> auction;

        @JsonIgnore
        @OneToMany(mappedBy = "annonce")
        private List<Favorite> favorites;


}