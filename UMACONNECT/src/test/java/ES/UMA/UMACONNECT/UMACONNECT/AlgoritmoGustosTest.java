package ES.UMA.UMACONNECT.UMACONNECT;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoFiltro;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoGustos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AlgoritmoGustosTest {
    String gustopropio = "cine";
    String gustoexterno = "cine";
    String gustoexternonoigual = "deporte";
    UserRepository repo;
    AlgoritmoGustos alg;
    User usuario;

    @BeforeEach
    public void init() {
        repo = mock (UserRepository.class);
        alg = new AlgoritmoGustos(repo);
        usuario = new User();
    }

    @Test
    public void recomendarGustosFunciona() {
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
    public void getMinGustosDevuelve3() {
        assertEquals(alg.getMinGustos(), 3);
    }

    @Test
    public void getGustosCompartidosDevuelveInt() {
        assertEquals(alg.getGustosCompartidos(), 0);
    }

}
