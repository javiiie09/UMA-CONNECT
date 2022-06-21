package ES.UMA.UMACONNECT.UMACONNECT.ENTITY;

import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String username;
	
	@Column(nullable = false, unique = false, length = 45)
	private String nombre;
	
	@Column(nullable = false, unique = false, length = 45)
	private String apellido1;
	
	@Column(nullable = true, unique = false, length = 45)
	private String apellido2;
	
	@Column(nullable = false, unique = false, length = 100)
	private String password;
	
	@Column(nullable = true, unique = false)
	private boolean enabled;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = true, unique = false)
	private String gustos;
	
	@Column(nullable = true, unique = false)
	private int edad;
	
	@Column(nullable = true, unique = false)
	private String facultad;
	
	@Column(nullable = true, unique = false)
	private Clob user_pic;
	
	@Column(nullable = true, unique = false)
	private String desc;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getGustos() {
		return gustos;
	}
	
	public void setGustos(String gustos) {
		this.gustos = gustos;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getFacultad() {
		return facultad;
	}
	
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	
	public Clob getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(Clob user_pic) {
		this.user_pic = user_pic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 :id.hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    User other = (User) obj;
	    if (id == null) {
	        if (other.id != null)
	            return false;
	        } else if (!id.equals(other.id))
	            return false;
	        return true;
	}
	
	@Override
	public String toString() {
	    return nombre + " " + apellido1 + " " + apellido2;
	}
	
}
