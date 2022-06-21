package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;

class UsuarioAux {
	private User usuario;
	private int puntos;
	
	public UsuarioAux(User usuario, int puntos) {
		this.usuario=usuario;
		this.puntos=puntos;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public User getUsuario() {
		return usuario;
	}
	
	//Convierte una lista de UsuariosAux en usuarios, hasta un máximo
	public static List<User> elegirPrimeros(List<UsuarioAux> recomendados, int max){
		List<User> res = new ArrayList<>();
		Iterator<UsuarioAux> it = recomendados.iterator();
		for(int i = 0; i < max && it.hasNext(); i++) {
			it.next();
			res.add(recomendados.get(i).getUsuario());
		}
		return res;
	}
	
	// Objeto de tipo comparator para clase UsuarioAux
	public static Comparator<UsuarioAux> comparador = new Comparator<UsuarioAux>() {
		public int compare(UsuarioAux u1, UsuarioAux u2) {
			if(u1.getPuntos()<u2.getPuntos()) {
				return 1;
			}else if(u1.getPuntos()>u2.getPuntos()) {
				return -1;
			}else {
				return 0;
			}
		}
	};
}