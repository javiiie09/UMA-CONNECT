package ES.UMA.UMACONNECT.UMACONNECT.ENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensajeria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = false, length = 45)
	private String usuario_Emisor;
	
	@Column(nullable = false, unique = false, length = 45)
	private String usuario_Receptor;
	
	@Column(nullable = false, unique = false, length = 100)
	private String mensaje;

	@Column
	private LocalDateTime fechaHora;
	
	public Mensajeria(String usuarioEmisor, String usuarioReceptor, String mensaje) {
		this.usuario_Emisor=usuarioEmisor;
		this.usuario_Receptor=usuarioReceptor;
		this.mensaje=mensaje;
		fechaHora=LocalDateTime.now();
	}
	
	public Mensajeria() {
		
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario_Emisor() {
		return usuario_Emisor;
	}

	public void setUsuario_Emisor(String usuario_Emisor) {
		this.usuario_Emisor = usuario_Emisor;
	}

	public String getUsuario_Receptor() {
		return usuario_Receptor;
	}

	public void setUsuario_Receptor(String usuario_Receptor) {
		this.usuario_Receptor = usuario_Receptor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
