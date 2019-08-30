package controller;

import forum.game.domain.dao.ITagDAO;
import forum.games.domain.dao.postgres.TagDaoImpl;
import forum.games.domain.entities.Tag;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tag")
public class TagController {
    private ITagDAO dataBase = new TagDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag select(@PathParam("id")int pk){
        for(Tag element : dataBase.show()){
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
            Tag aux = new Tag();
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
