package controller;
/* annotation jersey ci permetteranno di 
 * 
 * 1) per gogno metodo creare un path url diverso
 * 2) a secondo del servizio quale metodo http da implementare(get-post-put delete)
 * 3) per ogni metodo se produce o consuma qualcosa ed il formato di quello che produce e/o consuma 
 * (get = produce)
 * (post= consuma )
 * indirizzo di tomcat per questa applicazione
 * 
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
// https://localhost:8080//PerRest/rest/ciao
// per aggiugere questa classe al path dopo il rest

// per testare il servizio nel terminale  curl http://localhost:8080//PerRest/rest/ciao
@Path("/ciao")
public class HelloResource {
	@Path("/saluto")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ritorno(){
	 return "Ciao";
		
	}
	
	@Path("/saluto2/{nome}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ritorno2(@PathParam("nome")String nome){
	 return "Ciao";
		
	}
}
