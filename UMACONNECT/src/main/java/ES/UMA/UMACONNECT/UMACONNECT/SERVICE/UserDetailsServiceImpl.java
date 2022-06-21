package ES.UMA.UMACONNECT.UMACONNECT.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
	
    @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User appUser = userRepository.findByUsername(username).orElseThrow(() 
    			-> new UsernameNotFoundException("No existe usuario"));
		
    UserDetails user = User.withUsername(appUser.getUsername()).password(appUser.getPassword()).authorities("User").build();     
    	return user;    
    }
}
