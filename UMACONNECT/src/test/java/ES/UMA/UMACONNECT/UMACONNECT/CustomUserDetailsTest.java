package ES.UMA.UMACONNECT.UMACONNECT;

import java.util.Collection;

import ES.UMA.UMACONNECT.UMACONNECT.SERVICE.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomUserDetailsTest {
    CustomUserDetails detalles1;
    CustomUserDetails detalles2;

    @BeforeEach
    public void init() {
        User user1 = new User();
        user1.setPassword("hola");
        user1.setUsername("paquitopotencias");
        user1.setEmail("a@a");
        user1.setGustos("cine");
        user1.setNombre("Paco");
        user1.setApellido1("Ruiz");
        user1.setApellido2("Hernandez");
        detalles1 = new CustomUserDetails(user1);
    }

    @Test
    public void getAuthoritiesDevuelveNull() {
        assertEquals(detalles1.getAuthorities(), null);
    }

    @Test
    public void getPasswordDevuelvePassword() {
        assertEquals(detalles1.getPassword(), "hola");
    }

    @Test
    public void getUsernameDevuelveUsername() {
        assertEquals(detalles1.getUsername(), "paquitopotencias");
    }

    @Test
    public void getEmailDevuelveEmail() {
        assertEquals(detalles1.getEmail(), "a@a");
    }

    @Test
    public void getGustosDevuelveGustos() {
        assertEquals(detalles1.getGustos(), "cine");
    }

    @Test
    public void isAccountNonExpiredDevuelveTrue() {
        assertTrue(detalles1.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLockedDevuelveTrue() {
        assertTrue(detalles1.isAccountNonLocked());
    }

    @Test
    public void isCredentialNonExpiredDevuelveTrue() {
        assertTrue(detalles1.isCredentialsNonExpired());
    }

    @Test
    public void getFullNameDevuelveNombre() {
        assertEquals(detalles1.getFullName(), "Paco Ruiz Hernandez");
    }

    @Test
    public void isEnabledDevuelveTrue() {
        assertTrue(detalles1.isEnabled());
    }

}
