package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.ArrayList;
import java.util.List;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.Mensajeria;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.MensajeriaRepository;

public class ObtenedorMensajes {
	public List<List<String>> obtenerMensajes(MensajeriaRepository mensajeRepo, String emisor, String receptor){
		List<String> mensajesEnviados = new ArrayList<String>();
		List<String> mensajesRecibidos = new ArrayList<String>();
		List<Mensajeria> todosMensajes = mensajeRepo.findAll();
		for(int i=0; i<todosMensajes.size(); i++) {
			if(todosMensajes.get(i).getUsuario_Emisor().equals(emisor) && todosMensajes.get(i).getUsuario_Receptor().equals(receptor)) {
				mensajesEnviados.add(todosMensajes.get(i).getMensaje());
			}else if(todosMensajes.get(i).getUsuario_Receptor().equals(emisor) && todosMensajes.get(i).getUsuario_Emisor().equals(receptor)) {
				mensajesRecibidos.add(todosMensajes.get(i).getMensaje());
			}
		}
		List<List<String>> mensajes = new ArrayList<>();
		mensajes.add(mensajesEnviados);
		mensajes.add(mensajesRecibidos);
		return mensajes;
	}
}
