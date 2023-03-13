package com.authentification.entities;

import javax.persistence.*;

@Entity
@Table(name="favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favorite_id ;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name="annonce_id")
    private Annonce annonce ;

    public Long getFavorite_id() {return favorite_id;}
    public User getUser() {return user;}
    public Annonce getAnnonce() {return annonce;}

    public void setFavorite_id(Long favorite_id) {this.favorite_id = favorite_id;}
    public void setUser(User user) {this.user = user;}
    public void setAnnonce(Annonce annonce) {this.annonce = annonce;}

    public Favorite(Long favorite_id, User user, Annonce annonce) {this.favorite_id = favorite_id;
                                                                   this.user = user;
                                                                   this.annonce = annonce;}
    public Favorite() {}
}
