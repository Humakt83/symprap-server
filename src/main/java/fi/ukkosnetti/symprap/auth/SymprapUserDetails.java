package fi.ukkosnetti.symprap.auth;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import fi.ukkosnetti.symprap.model.UserRole;

@SuppressWarnings("serial")
public class SymprapUserDetails implements UserDetails {
	
	private final String userName;
	
	private final String password;
	
	private final Collection<GrantedAuthority> authorities;
	
	public SymprapUserDetails(String userName, String password, Set<UserRole> roles) {
		this.userName = userName;
		this.password = password;
		String[] rolesConverted = roles.stream().map(role -> role.toString()).toArray(size -> new String[size]);
		authorities = AuthorityUtils.createAuthorityList(rolesConverted);
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

}
