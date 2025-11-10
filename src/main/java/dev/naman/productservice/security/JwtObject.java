package dev.naman.productservice.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtObject {
	private String email;
	private Long userId;
	private Date createdAt;
	private Date expiryAt;
	private List<Role> roles = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiryAt() {
		return expiryAt;
	}

	public void setExpiryAt(Date expiryAt) {
		this.expiryAt = expiryAt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}