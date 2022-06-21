package ES.UMA.UMACONNECT.UMACONNECT;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import ES.UMA.UMACONNECT.UMACONNECT.CONFIG.webSecurityConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class webSecurityConfigTest {
    webSecurityConfig config1, config2;

    @BeforeEach
    public void init(){
        config1 = new webSecurityConfig();
        config2 = new webSecurityConfig();
    }

    @Test
     public void testConfiguracion() throws Exception {
        config1.configure(null);
        config2.configure(null);
    }

    @Test
    void passwordEncoderNuncaDebeSerIgual() {
        assertNotEquals (config1.passwordEncoder(), config2.passwordEncoder());
    }
}
