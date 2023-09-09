package com.drema.service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruya_tabir")
public class Ruya_Tabir_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruyaId;

    private String ruya;

    private String tabir;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Ruya_User_Model user;

    private String dateAsked;

    private String status;

	public Long getRuyaId() {
		return ruyaId;
	}

	public void setRuyaId(Long ruyaId) {
		this.ruyaId = ruyaId;
	}

	public String getRuya() {
		return ruya;
	}

	public void setRuya(String ruya) {
		this.ruya = ruya;
	}

	public String getTabir() {
		return tabir;
	}

	public void setTabir(String tabir) {
		this.tabir = tabir;
	}

	public Ruya_User_Model getUser() {
		return user;
	}

	public void setUser(Ruya_User_Model user) {
		this.user = user;
	}

	public String getDateAsked() {
		return dateAsked;
	}

	public void setDateAsked(String dateAsked) {
		this.dateAsked = dateAsked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
}