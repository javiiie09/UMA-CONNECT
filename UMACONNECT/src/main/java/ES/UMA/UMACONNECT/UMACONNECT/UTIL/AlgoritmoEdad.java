package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;

/*
 *	Algoritmo recomendador alternativo
 *	Este algoritmo es otra estrategia de recomendación. Se basa en la edad y en parte en gustos
 *	Lo principal es la edad. Una diferencia de edad de 0 años: da 100 puntos. Por cada año de diferencia se restan 10 punto
 *	Por otra parte, los gustos comunes dan 1 puntos pero solo cuentan hasta 2 gustos, más de 2 da igual.
 */

public class AlgoritmoEdad implements IRecomendador{
	
	private List<User> recomend;
	
	private int gustosCompartidos;
	private int nUsuarios = 8;
	
	@Autowired
    private UserRepository userRepo;
	
	public AlgoritmoEdad (UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	private void compararGustos(String gustos, String gustosAjenos) {
		
		final String SEP= "\\s*[,]\\s*";
		String[] lista1 = gustos.split(SEP);
		String[] lista2 = gustosAjenos.split(SEP);

		for (String item:lista1) {
			boolean match = Arrays.stream(lista2).anyMatch(item::equalsIgnoreCase);
			if (match) ++gustosCompartidos;	
		}	
	}
	
	private int puntosPorEdad(int edad1, int edad2) {
		return 100 - 10*Math.abs(edad1-edad2);
	};
	
	// da puntos por gustos iguales, pero sólo hasta 2 iguales, el resto no importan
	private int puntosPorGustos(String gustos, String gustosAjenos) {
		compararGustos(gustos, gustosAjenos);
		return Math.min(gustosCompartidos,2);
	}
	
	public void recomendar(User user) {
		
		int MAXUSUARIOS=100;
		int nUsuarios = (int) userRepo.count();
		String gustos = user.getGustos();
		int edad = user.getEdad();
		Random r = new Random();
		List<UsuarioAux> recomendados = new ArrayList<>();
		while(recomendados.size() < nUsuarios) {
			try {
					for(int i=0; i<MAXUSUARIOS; i++) {
						int id = r.nextInt(nUsuarios-1)+1;
						//TODO: asegurar que no se repiten. probar con diccionario o set (mejor sorted set)
						User usuarioAjeno = userRepo.getById((long) id);
						String gustosAjenos = usuarioAjeno.getGustos();
						int edadAjena = usuarioAjeno.getEdad();
						
						int cnt = puntosPorGustos(gustos, gustosAjenos) + puntosPorEdad(edad, edadAjena);
						UsuarioAux usuario = new UsuarioAux(usuarioAjeno, cnt);
						if(recomendados.contains(usuario)) {
							recomendados.add(usuario);
						}
							
						if(i==MAXUSUARIOS-1 && recomendados.isEmpty()) {
							i=0;
						}
					}
			}catch(Exception e) {
				
			}
		
		}
		
		Collections.sort(recomendados, UsuarioAux.comparador);
		recomend =  UsuarioAux.elegirPrimeros(recomendados, 10);
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

	public int getGustosCompartidos() {
		return gustosCompartidos;
	}

	

}