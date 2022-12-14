package com.sans.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;


@Data
public class SelfUserEntity implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;


	private Long userId;

	private String username;

	private String password;

	private String status;


	private Collection<GrantedAuthority> authorities;

	private boolean isAccountNonExpired = false;

	private boolean isAccountNonLocked = false;

	private boolean isCredentialsNonExpired = false;

	private boolean isEnabled = true;


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
