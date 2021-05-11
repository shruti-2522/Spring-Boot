package com.validation.entities;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginData {

	@NotBlank(message = "user name can not be empty!!")

	private String uname;
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Invalid Email")
	private String email;

	@AssertTrue(message="Must agree terms and conditions!!")
	private boolean agreed;
	public boolean isAgreed() {
		return agreed;
	}

	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "LoginData [uname=" + uname + ", email=" + email + ", agreed=" + agreed + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
