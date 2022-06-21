package ES.UMA.UMACONNECT.UMACONNECT.CONTROLLER;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.Chats;
import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.ChatRepository;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.MensajeriaRepository;
import ES.UMA.UMACONNECT.UMACONNECT.REPOSITORY.UserRepository;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.AlgoritmoGustos;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.Frontend;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.IRecomendador;
import ES.UMA.UMACONNECT.UMACONNECT.UTIL.Imagen;



@Controller
public class umaconnectController {
	
	private User usuario;
	
	private User usuarioChat;
	
	private Chats chatAux;
	
	@Autowired
    private UserRepository userRepo;
	
	@Autowired
    private ChatRepository chatRepo;

	@Autowired
	private MensajeriaRepository mensajeRepo;
	
    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }
        
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
        
    @PostMapping("/process_register")    
    public String processRegister(User user, @RequestParam("image") MultipartFile multipartFile) throws SerialException, IOException, URISyntaxException, SQLException {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Clob foto = Imagen.imageToClob(multipartFile.getInputStream());
	
        user.setUser_pic(foto);
        user.setPassword(encodedPassword);
        userRepo.save(user);

        return "redirect:/login";
    }
    
    @GetMapping("/preferences")
    public String showPreferences(WebRequest request) {    	
    	Optional<User> u = userRepo.findByUsername(request.getRemoteUser());
    	usuario = userRepo.getById(u.get().getId());
    	
    	if(!usuario.isEnabled()) {
    		return "preferences";
    	}else {
    		return "redirect:/home";
    	}
    }
    
    @PostMapping("/process_preferences")
    public String processPreferences(WebRequest request, Model model) {    	
    	String gustos;
    	Map<String, String[]> allParams = request.getParameterMap();
    	StringJoiner sj = new StringJoiner(",");
    	
    	for (String key : allParams.keySet()) {
    		if(!key.equals("button")) {
    			sj.add(key);
    		}
    	}
    	
    	gustos = sj.toString();
    	usuario.setEnabled(true);
    	usuario.setGustos(gustos);
    	userRepo.save(usuario);
    	  	
    	return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String menu(Model model) throws SerialException, IOException, SQLException {
		List<String> gustosclass = new ArrayList<>();
		
		String facultadclass = null;
    	String nom = null;
		String gus = null;
		String nomed = null;
		String facultad = null;
		String pic = null;
		String usernom = null;
		String us = null;
		
    	IRecomendador a = new AlgoritmoGustos(userRepo);
		//IRecomendador a = new AlgoritmoEdad(userRepo);
    	//IRecomendador a = new AlgoritmoFiltro(userRepo, "deporte");
		a.recomendar(usuario);
		
    	for(int i = 0; i < a.getRecomend().size(); ++i) {
    		
    		nom = String.format("nom%d", i);
    		us = String.format("us%d", i);
    		nomed = a.getRecomend().get(i).getUsername() + ", " + a.getRecomend().get(i).getEdad();
    		usernom = a.getRecomend().get(i).getUsername();
    		model.addAttribute(nom, nomed);
    		model.addAttribute(us, usernom);
    		
    		facultad = String.format("fac%d", i);
    		facultadclass = Frontend.facultad_to_class(a.getRecomend().get(i).getFacultad());
			model.addAttribute(facultad, facultadclass);
			
			Clob foto = Imagen.generateImage();
			pic = String.format("pic%d", i);
			a.getRecomend().get(i).setUser_pic(foto);
			userRepo.save(a.getRecomend().get(i));			
			model.addAttribute(pic, Imagen.clobToString(a.getRecomend().get(i).getUser_pic())); //Nos quedamos esta linea cuando tengamos BBDD persistente
			
			gustosclass = Frontend.gustos_to_class(a.getRecomend().get(i).getGustos());
			for(int j=0; j < gustosclass.size(); j++){
				gus = String.format("gus%d%d",i , j);
				model.addAttribute(gus, gustosclass.get(j));
			}
    	} 
        return "home";
    }
    
    @GetMapping("/user")
    public String user(Model model) throws SQLException, IOException {    	
    	
    	//Saca los datos del usuario para que los podamos poner donde queramos en el HTML con th:text
    	model.addAttribute("nom", usuario.getNombre());
    	model.addAttribute("ap1", usuario.getApellido1());
    	if(!(usuario.getApellido2() == null)) {
    		model.addAttribute("ap2", usuario.getApellido2());
    	}
    	model.addAttribute("em", usuario.getEmail());
    	model.addAttribute("ed", usuario.getEdad());
    	model.addAttribute("us", usuario.getUsername());
    	if(!(usuario.getUser_pic() == null)) {
    		model.addAttribute("pic", Imagen.clobToString(usuario.getUser_pic()));
    	}
    	
    	model.addAttribute("facu", Frontend.facultad_to_class(usuario.getFacultad()));
    	
    	if(usuario.getDesc() != null) {
    		model.addAttribute("descrip", usuario.getDesc());
    	}
    	
        return "user";
    }
    
    @PostMapping("/process_user")
    public String processUser(Model model, WebRequest request, @RequestParam(value="image", required = false) MultipartFile multipartFile) throws SerialException, IOException, URISyntaxException, SQLException {
    	Map<String, String[]> allParams = request.getParameterMap();
    	for(String key : allParams.keySet()) {
    		if(key.equals("nombre")) {
    			usuario.setNombre(allParams.get(key)[0]);
    		}
    		if(key.equals("apellido1")) {
    			usuario.setApellido1(allParams.get(key)[0]);
    		}
    		if(key.equals("apellido2")) {
    			usuario.setApellido2(allParams.get(key)[0]);
    		}
    		if(key.equals("email")) {
    			usuario.setEmail(allParams.get(key)[0]);
    		}
    		if(key.equals("edad")) {
    			usuario.setEdad(Integer.parseInt(allParams.get(key)[0]));
    		}
    		if(key.equals("desc")) {
    			usuario.setDesc(allParams.get(key)[0]);
    		}
    		if(key.equals("fac")) {
    			usuario.setFacultad(allParams.get(key)[0]);
    		}
    		if(key.equals("username")) {
    			usuario.setUsername(allParams.get(key)[0]);
    			@SuppressWarnings("unchecked")
				Collection<SimpleGrantedAuthority> nowAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(allParams.get(key)[0], usuario.getPassword(), nowAuthorities);
    			SecurityContextHolder.getContext().setAuthentication(authentication);
    		}
    		if(key.equals("password")) {
    			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	        String encodedPassword = passwordEncoder.encode(allParams.get(key)[0]);
    	        usuario.setPassword(encodedPassword);
    		}    		
    	}
    	
    	if(multipartFile != null) {
            Clob foto = Imagen.imageToClob(multipartFile.getInputStream());
            usuario.setUser_pic(foto);
        }
    	
    	userRepo.save(usuario);
    	
		return "redirect:/user";    
    }
    
    @GetMapping("/profile/{us}")
    public String userAjeno(@PathVariable(value="us") String us, Model model) throws SQLException, IOException {
    	Optional<User> u = userRepo.findByUsername(us);
    	usuarioChat = userRepo.getById(u.get().getId());  
    	model.addAttribute("chats", new Chats(usuario.getId()));
    	
    	List<String> gustosclass = new ArrayList<>();
    	
    	//Saca los datos del usuario para que los podamos poner donde queramos en el HTML con th:text
    	model.addAttribute("nom", u.get().getNombre());
    	model.addAttribute("ap1", u.get().getApellido1());
    	if(!(u.get().getApellido2() == null)) {
    		model.addAttribute("ap2", u.get().getApellido2());
    	}
    	model.addAttribute("em", u.get().getEmail());
    	model.addAttribute("ed", u.get().getEdad());
    	model.addAttribute("u", u.get().getUsername());
    	if(!(u.get().getUser_pic() == null)) {
    		model.addAttribute("pic", Imagen.clobToString(u.get().getUser_pic()));
    	}
    	
    	model.addAttribute("facu", Frontend.facultad_to_class(u.get().getFacultad()));
    	
    	if(u.get().getDesc() != null) {
    		model.addAttribute("descrip", u.get().getDesc());
    	}
    	String gus = null;
    	gustosclass = Frontend.gustos_to_class(u.get().getGustos());
    	
    	for(int j=0; j < gustosclass.size(); j++){
			gus = String.format("gus%d", j);
			model.addAttribute(gus, gustosclass.get(j));
		}
    	
    	return "profile";
    }
    
    @PostMapping("/process_profile")
    public String processUserAjeno(Chats chats, Model model, WebRequest request) {
    	if(!chatRepo.existsById(usuario.getId())) {
    		Chats chat = new Chats(usuario.getId());
    		chatAux = chat;
    		String[] array = new String[1];
    		array[0] = usuarioChat.getUsername();
            chatAux.setuAjeno(array);
    	}else {
    		chatAux = chatRepo.getById(usuario.getId());
    		String[] aux = chatAux.getuAjeno();
    		String[] array = new String[aux.length + 1];
    		for(int i = 0; i < aux.length; ++i) {
    			array[i] = aux[i];
    		}
    		array[array.length-1] = usuarioChat.getUsername();
            chatAux.setuAjeno(array);
    	}
    	chatRepo.save(chatAux);
        
        return "redirect:/chat";
    }
    
    @GetMapping("/chat")
    public String mensajeria(Model model) throws SQLException, IOException {
    	Optional<Chats> c = chatRepo.findByIDUser(usuario.getId());
    	chatAux = chatRepo.getById(c.get().getIDUser());
    	
    	String uPic = null;
    	String uUsName = null;
    	
    	if(!(usuario.getUser_pic() == null)) {
    		model.addAttribute("pic", Imagen.clobToString(usuario.getUser_pic()));
    	}
    	
    	for(int i = 0; i < chatAux.getuAjeno().length; i++) {
    		uPic = String.format("pic%d", i);
    		uUsName = String.format("us%d", i);
    		
    		model.addAttribute(uPic, Imagen.clobToString(userRepo.findByUsername(chatAux.getuAjeno()[i]).get().getUser_pic()));
    		model.addAttribute(uUsName, userRepo.findByUsername(chatAux.getuAjeno()[i]).get().getUsername());
    	}
    	
    	
    	
    	return "mensajeria";
    }
}
