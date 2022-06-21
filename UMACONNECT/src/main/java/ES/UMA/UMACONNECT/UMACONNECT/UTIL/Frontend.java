package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frontend {

    static public List<String> gustos_to_class(String gustos){
        List<String> gustoslista = Arrays.asList(gustos.split(","));
        List<String> res = new ArrayList<>();

        for ( String g : gustoslista) {
            
                if(g.equalsIgnoreCase("Deporte")){
                    res.add("fa-baseball");
                }

                if(g.equalsIgnoreCase("Videojuegos")){
                    res.add("fa-gamepad");
                }

                if(g.equalsIgnoreCase("Musica")){
                    res.add("fa-music");
                }

                if(g.equalsIgnoreCase("Baile")){
                    res.add("fa-icons");
                }

                if(g.equalsIgnoreCase("Programacion")){
                    res.add("fa-keyboard");
                }
                if(g.equalsIgnoreCase("Arte")){
                    res.add("fa-palette");
                }
                if(g.equalsIgnoreCase("Series")){
                    res.add("fa-tv");
                }
                if(g.equalsIgnoreCase("Cocina")){
                    res.add("fa-utensils");
                }
                if(g.equalsIgnoreCase("Animales")){
                    res.add("fa-dog");
                }
                if(g.equalsIgnoreCase("Viajar")){
                    res.add("fa-plane");
                }
                if(g.equalsIgnoreCase("juegosmesa")){
                    res.add("fa-chess");
                }
                if(g.equalsIgnoreCase("Literatura")){
                    res.add("fa-book");
                }
        }
        return res;
    }
    
    static public String facultad_to_class(String facultad){
    	String res = null;
    	if(facultad.equalsIgnoreCase("EII")){
            res = "Escuela de Ingenierías Industriales";
        }
    	if(facultad.equalsIgnoreCase("EA")){
            res = "E.T.S. de Arquitectura";
        }
    	if(facultad.equalsIgnoreCase("EIT")){
            res = "E.T.S. de Ingeniería de Telecomunicación";
        }
    	if(facultad.equalsIgnoreCase("EIINF")){
            res = "E.T.S. de Ingeniería Informática";
        }
    	if(facultad.equalsIgnoreCase("FBA")){
            res = "Facultad de Bellas Artes";
        }
    	if(facultad.equalsIgnoreCase("FC")){
            res = "Facultad de Ciencias";
        }
    	if(facultad.equalsIgnoreCase("FCC")){
            res = "Facultad de Ciencias de la Comunicación";
        }
    	if(facultad.equalsIgnoreCase("FCE")){
            res = "Facultad de Ciencias de la Educación";
        }
    	if(facultad.equalsIgnoreCase("FCS")){
            res = "Facultad de Ciencias de la Salud";
        }
    	if(facultad.equalsIgnoreCase("FCEE")){
            res = "Facultad de Ciencias Económicas y Empresariales";
        }
    	if(facultad.equalsIgnoreCase("FCG")){
            res = "Facultad de Comercio y Gestión";
        }
    	if(facultad.equalsIgnoreCase("FD")){
            res = "Facultad de Derecho";
        }
    	if(facultad.equalsIgnoreCase("FEST")){
            res = "Facultad de Estudios Sociales y del Trabajo";
        }
    	if(facultad.equalsIgnoreCase("FFL")){
            res = "Facultad de Filosofía y Letras";
        }
    	if(facultad.equalsIgnoreCase("FM")){
            res = "Facultad de Medicina";
        }
    	if(facultad.equalsIgnoreCase("FPL")){
            res = "Facultad de Psicología y Logopedia";
        }
    	if(facultad.equalsIgnoreCase("FT")){
            res = "Facultad de Turismo";
        }
    	return res;    	
    }
}
