package com.ata.flo.security;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.ata.flo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	USER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, ARTICLE_READ, ARTICLE_WRITE)),
	HALFADMIN(Sets.newHashSet(USER_READ,ARTICLE_READ));
	
	private final Set<ApplicationUserPermission> permissions;
	
	
	ApplicationUserRole(Set<ApplicationUserPermission> peromissions) {
		this.permissions = peromissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
