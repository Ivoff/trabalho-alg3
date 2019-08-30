package controller;

import forum.game.domain.dao.IPublisherDAO;
import forum.games.domain.dao.postgres.PublisherDaoImpl;
import forum.games.domain.entities.Publisher;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/publisher")
public class PublisherController {
    private IPublisherDAO dataBase = new PublisherDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publisher> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher select(@PathParam("id")int pk){
        for(Publisher element : dataBase.show()){
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
            Publisher aux = new Publisher();
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
