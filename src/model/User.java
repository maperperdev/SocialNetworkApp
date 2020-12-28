package model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String nick;
	private String email;
	private Date dateBirth;
	private Date dateRegister;
	private String password;
	private String bio;
	
	public User(){}

	public User(String nick, String email, Date dateBirth, Date dateRegister, String password,
			String bio) {
		this.nick = nick;
		this.email = email;
		this.dateBirth = dateBirth;
		this.dateRegister = dateRegister;
		this.password = password;
		this.bio = bio;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}


	
	@Override
	public String toString() {
		return "JbUsuario [nick=" + nick + ", email=" + email + ", dateBirth=" + dateBirth
				+ ", dateRegister=" + dateRegister + ", password=" + password + ", bio=" + bio + "]";
	}

}
