package controller;

import forum.game.domain.dao.IDeveloperDAO;
import forum.games.domain.dao.postgres.DeveloperDaoImpl;
import forum.games.domain.entities.Developer;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Developer")
public class DeveloperController {
    private IDeveloperDAO dataBase = new DeveloperDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer select(@PathParam("id")int pk){
        for(Developer element : dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET    
    @Path("/create/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("name")String name){
        try{
            Developer aux = new Developer();
            aux.setName(name);
            dataBase.insert(aux);
        }catch(Exception e){
            return "{\"status\": 0}";
        }
        String response = "{\"Status\": 1}";
        return response;
    }
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id")int pk){
        try{
            dataBase.delete(pk);
        }catch(Exception e){
            return "{\"status\": 0}";
        }
        String response = "{\"Status\": 1}";
        return response;
    }
}
