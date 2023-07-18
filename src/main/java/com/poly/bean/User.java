package com.poly.bean;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotBlank(message = "Không được để trống")
	String id;
	String password;
	@NotBlank(message = "Không được để trống")
	String fullname;
	@NotBlank(message = "Không được để trống")
	@Email(message = "Không đúng định dạng email")
	String email;
	@NotNull(message = "Vui lòng chọn giới tính")
	Boolean gender;
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday")
	@NotNull(message = "Vui lòng chọn ngày sinh")
	Date birthday = new Date();
	@NotBlank(message = "Không được để trống")
	String phone;
	@NotBlank(message = "Không được để trống")
	String address;
	@NotNull(message = "Vui lòng chọn quyền cho user")
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
