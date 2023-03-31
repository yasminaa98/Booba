package com.authentification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Annonce")
public class Annonce {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_annonce;

        @Column(name = "name")
        private String name;

        @Column(name = "state")
        private String state;

        @Column(name = "ageChild")
        private String ageChild;

        @Column(name = "ageToy")
        private String ageToy;

        @Column(name = "category")
        private String category;


        @Column(name = "description")
        private String description;

        private boolean estArchive;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_user")
        private User user;
        @OneToMany(mappedBy = "annonce")
        private List<Favorite> favorites;


}