package ES.UMA.UMACONNECT.UMACONNECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoEdad;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoFiltro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AlgoritmoFiltroTest {

    String gustotest= "cine";
    UserRepository repo;
    AlgoritmoFiltro alg;
    User usuario;

    @BeforeEach
    public void init() {
        repo = mock (UserRepository.class);
        alg = new AlgoritmoFiltro(repo, gustotest);
        usuario = new User();
    }

    @Test
    public void recomendarfunciona() {
        alg.recomendar(usuario);
    }

    @Test
    public void getRecomendVacio() {
        assertEquals(alg.getRecomend(), null);
    }

    @Test
    public void getnUsuariosDevuelveInt() {
        assertEquals(alg.getnUsuarios(), 8);
    }

    @Test
    public void getMinGustosDevuelveInt() {
        assertEquals(alg.getMinGustos(), 0);
    }

    @Test
    public void getGustosCompartidosDevuelveInt() {
        assertEquals(alg.getGustosCompartidos(), 0);
    }

}
