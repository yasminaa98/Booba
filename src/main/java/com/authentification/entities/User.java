package com.authentification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(	name = "users",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column (name="firstname")
	private String firstname;

	@Column (name="lastname")
	private String lastname ;

	@Column(name = "profilePicture")
	private String profilePicture;

	@Column (name="email")
	private String email;

	@Column (name="homeAddress")
	private String homeAddress ;

	@Column (name="phone")
	private int phone ;

	@Column (name="avgResponseTime")
	private String avgResponseTime;

	@Column (name="description")
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Bid> bids;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Auction> auctions;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Annonce> annonces;

	public User(String username,String email, String firstname,
				String lastname,String profilePicturePath, String homeAddress, String avgResponseTime, int phone,
				String description, String encode) {

		this.username = username;
		this.password = encode;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.homeAddress = homeAddress;
		this.phone = phone;
		this.avgResponseTime = avgResponseTime;
		this.description = description;
		this.profilePicture=profilePicture;
	}


}