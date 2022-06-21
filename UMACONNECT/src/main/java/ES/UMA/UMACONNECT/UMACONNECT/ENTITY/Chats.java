package ES.UMA.UMACONNECT.UMACONNECT.ENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chats {
	
	@Id	
	@Column(nullable = true, unique = true)
	private Long IDUser;

	@Column(nullable = true, unique = false)
	private String[] uAjeno;
	
	public Chats(Long id) {
		IDUser = id;
	}
	
	public Chats() {
		
	}

	public Long getIDUser() {
		return IDUser;
	}

	public void setIDUser(Long iDUser) {
		IDUser = iDUser;
	}

	public String[] getuAjeno() {
		return uAjeno;
	}

	public void setuAjeno(String[] uAjeno) {
		this.uAjeno = uAjeno;
	}
}
