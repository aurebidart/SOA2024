package ar.edu.iua.iw3.backend.auth;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	// Agregados para que funcione la autenticacion
	private static final long serialVersionUID = 1181473083412294679L;

	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonExpired = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonLocked = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean credentialsNonExpired = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean enabled;

	public static final String VALIDATION_OK = "OK";
	public static final String VALIDATION_ACCOUNT_EXPIRED = "ACCOUNT_EXPIRED";
	public static final String VALIDATION_CREDENTIALS_EXPIRED = "CREDENTIALS_EXPIRED";
	public static final String VALIDATION_LOCKED = "LOCKED";
	public static final String VALIDATION_DISABLED = "DISABLED";

	public String validate() {
		if (!accountNonExpired)
			return VALIDATION_ACCOUNT_EXPIRED;
		if (!credentialsNonExpired)
			return VALIDATION_CREDENTIALS_EXPIRED;
		if (!accountNonLocked)
			return VALIDATION_LOCKED;
		if (!enabled)
			return VALIDATION_DISABLED;
		return VALIDATION_OK;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@Column(length = 255, nullable = false)
	private String name;

	@Column(length = 255, nullable = false)
	private String lastname;

	@Column(length = 255, nullable = false, unique = true)
	private String username;

	@Column(length = 255, nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userroles", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "idUser") }, inverseJoinColumns = {
					@JoinColumn(name = "idRole", referencedColumnName = "id") })
	private Set<Role> roles;

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		return authorities;
	}

	public Set<String> getAuthoritiesStr() {
		return getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet());
	}

}
