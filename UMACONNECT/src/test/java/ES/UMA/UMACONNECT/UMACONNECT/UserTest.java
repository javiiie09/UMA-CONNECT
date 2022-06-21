package ES.UMA.UMACONNECT.UMACONNECT;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;


@SpringBootTest

public class UserTest {

    User u;
    @BeforeEach
	public void init() {
		u= new User();
	}
	@AfterEach
	public void terminate() {
		u = null;
	}

    @Test
    public void toStringIsCorrect(){
        long a=1;
        String name="Juan";
        String password="111";
        String apellido1="Rodriguez";
        String apellido2="Sanchez";

        u.setPassword(password);
        u.setId(a);
        u.setNombre(name);
        u.setApellido1(apellido1);
        u.setApellido2(apellido2);
        assertEquals(u.toString(),name + " " + apellido1 + " " + apellido2);

    }
    @Test
    public void testEqualsToSameObj(){
        User v = new User();
        assertTrue(v.equals(u));
    }
    @Test
    public void IfNotSameObjOrNullFalse(){
        Object obj = new Object();
        assertFalse(obj.equals(u));
        User v = new User();
        v=null;
        assertFalse(u.equals(v));
    }
    @Test
    public void IfIdNullReturnFalse(){
        
        User v = new User();
        long a =1;
        v.setId(a);
        u.setId(null);
        assertFalse(u.equals(v));
    }
    @Test
    public void IfIdNullHashCodeReturns31(){
        u.setId(null);
        assertEquals(31, u.hashCode());
    }
    @Test
    public void setIdWorksWell(){
        u.setId(null);
        assertEquals(null,u.getId()); 
        long a=2;
        u.setId(a);
        assertEquals(2,u.getId());
    }
    @Test
    public void setUserameWorksWell(){
        u.setUsername(null);
        assertEquals(null,u.getUsername()); 
        String a="jose";
        u.setUsername(a);
        assertEquals("jose",u.getUsername());
    }
    @Test
    public void setPasswordWorksWell(){
        u.setPassword(null);
        assertEquals(null,u.getPassword()); 
        String a="111";
        u.setPassword(a);
        assertEquals("111",u.getPassword());
    }

    @Test
    public void setEnabledWorksWell(){
       
        boolean a =true;
        u.setEnabled(a);
        assertEquals(true,u.getEnabled());
    }
    @Test
    public void setNombreWorksWell(){
        u.setNombre(null);
        assertEquals(null,u.getNombre()); 
        String a="jose";
        u.setNombre(a);
        assertEquals("jose",u.getNombre());
    }
    @Test
    public void setEmailWorksWell(){
        u.setEmail(null);
        assertEquals(null,u.getEmail()); 
        String a="jose@gmail.com";
        u.setEmail(a);
        assertEquals("jose@gmail.com",u.getEmail());
    }
    @Test
    public void setApellido1WorksWell(){
        u.setApellido1(null);
        assertEquals(null,u.getApellido1()); 
        String a="garcia";
        u.setApellido1(a);
        assertEquals("garcia",u.getApellido1());
    }
    @Test
    public void setApellido2WorksWell(){
        u.setApellido2(null);
        assertEquals(null,u.getApellido2()); 
        String a="jimenez";
        u.setApellido2(a);
        assertEquals("jimenez",u.getApellido2());
    }
    @Test
    public void setGustosWorksWell(){
        u.setGustos(null);
        assertEquals(null,u.getGustos()); 
        String a="videojuegos";
        u.setGustos(a);
        assertEquals("videojuegos",u.getGustos());
    }
    @Test
    public void setEdadWorksWell(){
        
        int a=2;
        u.setEdad(a);
        assertEquals(2,u.getEdad());
    }
    @Test
    public void setFacultadWorksWell(){
        u.setFacultad(null);
        assertEquals(null,u.getFacultad()); 
        String a="ETSII";
        u.setFacultad(a);
        assertEquals("ETSII",u.getFacultad());
    }
    @Test
    public void setUserPicWorksWell(){
        u.setUser_pic(null);
        assertEquals(null,u.getUser_pic()); 
        java.sql.Clob user_pic = mock(java.sql.Clob.class);
        u.setUser_pic(user_pic);
        assertEquals(user_pic,u.getUser_pic());
    }
    @Test
    public void setDescWorksWell(){
        u.setDesc(null);
        assertEquals(null,u.getDesc()); 
        
        u.setDesc("Artista");
        assertEquals("Artista",u.getDesc());
    }
    
}
