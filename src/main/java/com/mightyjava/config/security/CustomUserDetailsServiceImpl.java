package com.mightyjava.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mightyjava.entity.CustomUserDetails;
import com.mightyjava.entity.User;
import com.mightyjava.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User name "+username+" not found");
		}
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user)); //OK
		//OR
		return new CustomUserDetails(user);
	}

	/*private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		if(user.getRole().getRoleName().equals("admin")) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}*/
}