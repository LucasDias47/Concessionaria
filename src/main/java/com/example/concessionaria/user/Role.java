package com.example.concessionaria.user;

public enum Role {
	ADMIN, USER;

	  public String getAuthority() {
	        return name();
	    }
}
