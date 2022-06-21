package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

public class AlgoritmoFiltro implements IRecomendador{
	
	private List<User> recomend;
	
	private int gustosCompartidos;
	private int nUsuarios = 8;
	private int minGustos = 0;

	@Autowired
    private UserRepository userRepo;
	
	private String gusto;
	
	public AlgoritmoFiltro (UserRepository userRepo, String gusto) {
		this.userRepo=userRepo;
		this.gusto=gusto;
	}
	
	// Devuelve el número de gustos comunes a las 2 listas: string con gustos separados por comas:
	private void compararGustos(String gusto, String gustosAjenos) {
		final String SEP= "\\s*[,]\\s*";
		String[] listaAjena = gustosAjenos.split(SEP);
		
		boolean match = Arrays.stream(listaAjena).anyMatch(gusto::equalsIgnoreCase);
		if (match) ++gustosCompartidos;
	}
	
	public void recomendar(User user) {
		
		int MAXUSUARIOS=100;
		int nUsuarios = (int) userRepo.count();
		Random r = new Random();
		List<UsuarioAux> recomendados = new ArrayList<>();
		while(recomendados.size() < nUsuarios) {
			try {
					for(int i=0; i<MAXUSUARIOS; i++) {
						int id = r.nextInt(nUsuarios-1)+1;
					
						User usuarioAjeno = userRepo.getById((long) id);
						String gustosAjenos = usuarioAjeno.getGustos();
						
						compararGustos(gusto, gustosAjenos);
						if(gustosCompartidos >= minGustos) {
							UsuarioAux usuario = new UsuarioAux(usuarioAjeno, gustosCompartidos);
							if(!recomendados.contains(usuario)) {
								recomendados.add(usuario);
							}
						}
					
						if(i==MAXUSUARIOS-1 && recomendados.isEmpty()) {
							i=0;
						}
					}
			}catch(Exception e) {
				
			}
		}
		
		Collections.sort(recomendados, UsuarioAux.comparador);
		recomend =  UsuarioAux.elegirPrimeros(recomendados, nUsuarios);
	}
	
	public List<User> getRecomend() {
		return recomend;
	}

	public int getnUsuarios() {
		return nUsuarios;
	}

	public void setnUsuarios(int nUsuarios) {
		this.nUsuarios = nUsuarios;
	}

	public int getMinGustos() {
		return minGustos;
	}

	public void setMinGustos(int minGustos) {
		this.minGustos = minGustos;
	}

	public int getGustosCompartidos() {
		return gustosCompartidos;
	}


	
}