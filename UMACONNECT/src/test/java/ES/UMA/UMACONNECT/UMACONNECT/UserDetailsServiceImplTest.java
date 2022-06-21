package ES.UMA.UMACONNECT.UMACONNECT;

import ES.UMA.UMACONNECT.UMACONNECT.SERVICE.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDetailsServiceImplTest {

    @Test
    public void loadUserByUsernameNoDevuelveNadaSiNoExisteElUsuario() {
        UserDetailsServiceImpl test = new UserDetailsServiceImpl();
        assertEquals(test.loadUserByUsername(null), null);
    }
}
