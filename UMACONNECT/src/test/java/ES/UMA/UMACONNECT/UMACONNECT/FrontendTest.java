package ES.UMA.UMACONNECT.UMACONNECT;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import ES.UMA.UMACONNECT.UMACONNECT.UTIL.Frontend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


@SpringBootTest
public class FrontendTest {
    Frontend u;
    @BeforeEach
	public void init() {
		u= new Frontend();
	}
	@AfterEach
	public void terminate() {
		u = null;
	}
    @Test
    public void gustosToClassWorksWell(){
        
        List<String> prueba = new ArrayList<String>();
        prueba.add("fa-baseball");
        prueba.add("fa-music");
        prueba.add("fa-icons");
        String gustos= "Deporte,Musica,Baile";
        assertEquals(prueba,u.gustos_to_class(gustos));
    }
    @Test
    public void facultadToClassWorksWell(){
        
        
        String gustos= "EII";
        String prueba= "Escuela de Ingenierías Industriales";
        assertEquals(prueba,u.facultad_to_class(gustos));
    }
}
