package ES.UMA.UMACONNECT.UMACONNECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoEdad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AlgoritmoEdadTest {

    int edad1 = 18, edad2 = 19;
    UserRepository repo;
    AlgoritmoEdad alg;
    User usuario;

    @BeforeEach
    public void init() {
        repo = mock (UserRepository.class);
        alg = new AlgoritmoEdad(repo);
        usuario = new User();
    }

    @Test
    public void  recomendarFunciona() {
        alg.recomendar(usuario);
    }

    @Test
    public void getRecomendDevuelveRecomendacionQueSeraNull() {
        assertEquals(alg.getRecomend(), null);
    }

    @Test
    public void getnUsuariosDevuelvenUsuarios() {
        assertEquals(alg.getnUsuarios(), 8);
    }

    @Test
    public void getGustosCompartidosDevuelveGustos() {
        assertEquals(alg.getGustosCompartidos(), 0);
    }

}
