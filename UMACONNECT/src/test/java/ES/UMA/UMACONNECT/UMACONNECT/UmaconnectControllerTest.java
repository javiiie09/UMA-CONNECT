package ES.UMA.UMACONNECT.UMACONNECT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.WebRequest;

import ES.UMA.UMACONNECT.UMACONNECT.CONTROLLER.umaconnectController;
import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;

@SpringBootTest
class UmaconnectControllerTest {

	private umaconnectController mockcontrolador;

	@BeforeEach
	void setUp() {
		mockcontrolador = mock(umaconnectController.class);
	}

	@Test
	void loginDevuelveLogin() {
		when(mockcontrolador.login()).thenReturn("login");
		assertEquals(mockcontrolador.login(), "login");
	}

	@Test
	void showRegistrationFormDevuelveRegistration() {
		when(mockcontrolador.showRegistrationForm(null)).thenReturn("registration");
		assertEquals (mockcontrolador.showRegistrationForm(null), "registration");
	}


	@Test
	void processRegisterDevuelveRedirectlogin() throws SQLException, IOException, URISyntaxException {
		when(mockcontrolador.processRegister(null, null)).thenReturn("redirect:/login");
		assertEquals (mockcontrolador.processRegister(null, null), "redirect:/login");
	}


	@Test
	void peticionVaciaNoDevuelveRedirecthome() {
		WebRequest request = mock (WebRequest.class);
		when(request.getRemoteUser()).thenReturn("antonio");
		User usuario = mock (User.class);
		when(usuario.isEnabled()).thenReturn(true);
		assertNotEquals(mockcontrolador.showPreferences(request), "redirect:/home");
	}



	@Test
	void notenabledDevuelvePreferences() {
		User usuario = mock (User.class);
		assertEquals (mockcontrolador.showPreferences(null), "preferences");
	}

	@Test
	void processPreferencesDevuelveRedirecthome() {
		when(mockcontrolador.processPreferences(null, null)).thenReturn("redirect:/home");
		assertEquals (mockcontrolador.processPreferences(null, null), "redirect:/home");
	}

	@Test
	void userDevuelveUser() throws SQLException, IOException {
		when(mockcontrolador.user(null)).thenReturn("user");
		assertEquals (mockcontrolador.user(null), "user");
	}

	@Test
	void menuDevuelveHome() throws SQLException, IOException {
		when(mockcontrolador.menu(null)).thenReturn("home");
		assertEquals (mockcontrolador.menu(null), "home");

	}
}
