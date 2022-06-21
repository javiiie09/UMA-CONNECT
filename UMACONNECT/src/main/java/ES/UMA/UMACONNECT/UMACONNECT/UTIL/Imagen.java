package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

public class Imagen {
	
	private static User usuario;
	
	@Autowired
	private UserRepository userRepo;
	
	public Imagen (UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	public static Clob generateImage() throws IOException, SerialException, SQLException {
	    URL url = new URL("https://100k-faces.glitch.me/random-image");
	    InputStream in = new BufferedInputStream(url.openStream());
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    
	    byte[] buf = new byte[512];
	    int n = 0;
	    while (-1!=(n=in.read(buf)))
	    {
	       out.write(buf, 0, n);
	    }
	    out.close();
	    in.close();
	    byte[] response = out.toByteArray();
	    
	    String encodedString = Base64.getEncoder().encodeToString(response);
	    
	    Clob myClob = new javax.sql.rowset.serial.SerialClob(encodedString.toCharArray());
	    		
	    
	    return myClob;
	}
	
	
	public static Clob imageToClob(InputStream in) throws IOException, URISyntaxException, SerialException, SQLException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    
	    byte[] buf = new byte[512];
	    int n = 0;
	    while (-1!=(n=in.read(buf)))
	    {
	       out.write(buf, 0, n);
	    }
	    out.close();
	    in.close();
	    byte[] response = out.toByteArray();
	    String encodedString = Base64.getEncoder().encodeToString(response);
	    Clob myClob = new javax.sql.rowset.serial.SerialClob(encodedString.toCharArray());
	    
	    return myClob;
	}
    
    public static String clobToString(Clob c) throws SQLException, IOException {
    	Reader r = c.getCharacterStream();
    	StringBuffer buffer = new StringBuffer();
        int ch;
        while ((ch = r.read())!=-1) {
           buffer.append(""+(char)ch);
        }
        return buffer.toString();
    }
    
    public void imagenBD() {    	
    	for(int i = 1; i < (int) userRepo.count(); i++) {
			try {
				Clob foto = Imagen.generateImage();
				usuario=userRepo.getById((long) i);
				usuario.setUser_pic(foto);
				userRepo.save(usuario);		
			}catch(Exception e) {
				
			}
		}
		
		/*Clob foto = imagen.generateImage();
		a.getRecomend().get(i).setUser_pic(foto);
		userRepo.save(a.getRecomend().get(i));			
		model.addAttribute(pic, imagen.clobToString(a.getRecomend().get(i).getUser_pic()));*/
    }
}
