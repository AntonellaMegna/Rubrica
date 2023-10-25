package controller;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Contatti;
import service.Dao;
import service.EmailUtil;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
// https://localhost:8080//PerRest/rest/ciao
// per aggiugere questa classe al path dopo il rest

// per testare il servizio nel terminale  curl http://localhost:8080//Rubrica/rest/metodi

/*
 * GET
http://localhost:8080/nomeProgetto/rest/*
curl 
post dipendente 
curl -H "Content-Type: application/json"  -X POST -d "{\"nome\" : \"enzo\",\"cognome\" : \"di dona\",\"email\" : \"enzo@gmail.it\",\"pass\" : \"4321\"}" http://localhost:8080/nomeProgetto/rest/*

 * */
@Path("/metodi")
public class Servlet {
	
	ObjectMapper objectMapper = new ObjectMapper();
 //curl http://localhost:8080/Rubrica/rest/metodi/insertCon
	@Path("/insertCon")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean InsertC(Contatti c){
		boolean ok= false;
		ok= Dao.insertCon(c);
	 return ok;
		
	}
	//curl http://localhost:8080/Rubrica/rest/metodi/updateCont/lmallon1u@answers.com
	@Path("/updateCont")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCont(Contatti c){
		
		ObjectNode json=objectMapper.createObjectNode();
		 if(Dao.updateContatti(c)){
			 json.put("status", "ok");
		 }else{ json.put("status", "non ok");}
		 return Response.status(Response.Status.CREATED).entity(json).build();
	 
	}
	
	
	//curl http://localhost:8080/Rubrica/rest/metodi/selC/lmallon1u@answers.com
	@Path("/selC/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Contatti SelContatto(@PathParam("email")String email){
		return Dao.selectCon(email);
		
	}
 
	
	
//curl http://localhost:8080/Rubrica/rest/metodi/selN 
@Path("/selN/{first_name}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Contatti SelConNome(@PathParam("first_name")String first_name){
	return Dao.selectConLike(null, first_name);
	
}

//curl http://localhost:8080/Rubrica/rest/metodi/selN 
@Path("/selCog/{last_name}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Contatti SelConCogn(@PathParam("last_name")String last_name){
	return Dao.selectConLike( last_name,null);
	
}

	//

	
	@Path("/selAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contatti> SelAllC(){
		return Dao.selectConAll();
		
	}
	 
		
	@Path("/delC")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delcont(Contatti c){

 		final String fromEmail = "esercizioemailjava@gmail.com"; //requires valid gmail id
		final String password = "12xpoppex12"; // correct password for gmail id
		final String toEmail = "antonellamegna5@gmail.com"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
           //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, toEmail,"Cancellazione account", "Ci dispiace che ti sei cancellatto");
	 
		ObjectNode json=objectMapper.createObjectNode();
		 if(Dao.delCont(c)){
			 json.put("status", "ok");
		 }else{ json.put("status", "non ok");}
		 return Response.status(Response.Status.CREATED).entity(json).build();
	 
	}
	
	
	 
	
	
	
	
}
