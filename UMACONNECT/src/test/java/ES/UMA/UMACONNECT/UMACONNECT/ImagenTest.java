package ES.UMA.UMACONNECT.UMACONNECT;

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

import ES.UMA.UMACONNECT.UMACONNECT.UTIL.Imagen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ImagenTest {

    UserRepository repo;
    Imagen foto;


    @BeforeEach
    public void init() {

        foto = new Imagen(repo);
    }

    @Test
    public void GenerateImagenNuncaDevuelve2ClobsIguales() throws SQLException, IOException {
        assertNotEquals(foto.generateImage(), foto.generateImage());
    }

    @Test
    public void imageToClobNuncaDevuelve2ClobsIguales() throws SQLException, IOException, URISyntaxException {
        assertNotEquals(foto.imageToClob(null), foto.imageToClob(null));
    }

    @Test
    public void clobToString() throws SQLException, IOException {
        assertEquals(foto.clobToString(null), foto.clobToString(null));
    }

}
