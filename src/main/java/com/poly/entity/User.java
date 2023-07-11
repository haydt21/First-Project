package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	String id;
	String password;
	String fullname;
	String email;
	Boolean gender;
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday")
	Date birthday = new Date();
	String phone;
	String address;
	Boolean role;
	Boolean active;
	
	@Transient
	String confirmPassword;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Cart> carts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Favorite> favorites;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Order> orders;
}
