package com.mightyjava.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7302800336276816169L;
	private String username;
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id") // This FK column will be created in USER table
	private Role role;

}
