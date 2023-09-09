package com.drema.service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruya_user_token")
public class Ruya_User_Token_Model {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Ruya_User_Model user;

    private String token;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Ruya_User_Model getUser() {
		return user;
	}

	public void setUser(Ruya_User_Model user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
 
}