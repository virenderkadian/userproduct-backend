package com.sample.app01.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomAdmin implements UserDetails  {
	 private static final long serialVersionUID = -4709084843450077569L;  
	  private Long id; 
	  private String userName; 
	  private String email;
	  @JsonIgnore
	  private String password;
	  private Collection<? extends GrantedAuthority> authorities;
	  public CustomAdmin(long id, String userName, String email, 
	      String password){
	    this.id = id;
	    this.userName = userName;
	    this.email = email;
	    this.password = password;
	  }
	  
	  public static CustomAdmin createInstance(Register user) {
	    return new CustomAdmin(user.getId(), user.getAdminName(), 
	        user.getEmail(), user.getPassword());
	  }
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return userName;
	  }

	  public long getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
	  @Override
	  public boolean equals(Object rhs) {
	    if (rhs instanceof CustomAdmin) {
	      return userName.equals(((CustomAdmin) rhs).userName);
	    }
	    return false;
	  }

	  @Override
	  public int hashCode() {
	    return userName.hashCode();
	  }
}
