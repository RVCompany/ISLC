package com.drozd.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

import static com.drozd.bulider.PersonHelper.*;

public class UserService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

    @Autowired
	private PersonRepository personRepository;

    @Autowired
    private CarAttributeValueService carAttributeValueService;
	
	@PostConstruct	
	protected void initialize() {
		Account admin = accountRepository.save(getAdminAccount());
		Account user = accountRepository.save(getDefaultUserAccount());

        personRepository.save(getAdminPerson(admin));
        personRepository.save(getDefaultUserPerson(user));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(Account account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

}
