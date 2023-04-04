package com.authentification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
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


	@OneToMany(mappedBy = "user")
	private List<Annonce> annonce ;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	public User(String username,String email, String firstname,
				String lastname, String homeAddress, String avgResponseTime, int phone,
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
	}

	public User() {}


	@Override
	public String toString() {
		return "User{" +
				"id_user=" + id_user +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", homeAddress='" + homeAddress + '\'' +
				", phone=" + phone +
				", avgResponseTime='" + avgResponseTime + '\'' +
				", description='" + description + '\'' +
				", annonce=" + annonce +
				", favorites=" + favorites +
				'}';
	}
}