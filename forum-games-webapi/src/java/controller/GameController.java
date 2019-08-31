package controller;

import forum.game.domain.dao.IDeveloperDAO;
import forum.game.domain.dao.IGameDAO;
import forum.game.domain.dao.IPublisherDAO;
import forum.games.domain.dao.postgres.DeveloperDaoImpl;
import forum.games.domain.dao.postgres.GameDaoImpl;
import forum.games.domain.dao.postgres.PublisherDaoImpl;
import forum.games.domain.entities.Developer;
import forum.games.domain.entities.Game;
import forum.games.domain.entities.Publisher;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game")
public class GameController {
    private IGameDAO dataBase = new GameDaoImpl();
    private IDeveloperDAO developerBase = new DeveloperDaoImpl();
    private IPublisherDAO publisherBase = new PublisherDaoImpl();    
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game select(@PathParam("id")int pk){
        for(Game element : dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET
    @Path("/create/{name}/{id_developer}/{id_publisher}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("name")String name, @PathParam("id_developer")int developer_fk, @PathParam("id_publisher")int publisher_fk){
        try{
            Game aux = new Game();
            
            for(Developer developerElement : developerBase.show()){                
                System.out.println(developerElement.getId() == developer_fk);
                if(developerElement.getId() == developer_fk){
                    aux.setDeveloper(developerElement);
                }
            }
            
            for(Publisher publisherElement : publisherBase.show()){                
                System.out.println(publisherElement.getId() == publisher_fk);
                if(publisherElement.getId() == publisher_fk){
                    aux.setPublisher(publisherElement);
                }
            }
            
            aux.setName(name);
            
            dataBase.insert(aux);
        }catch(Exception e){
            return "{\"status\": 0}";
        }
        
        return "{\"status\": 1}";
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
        return "{\"Status\": 1}";
    }
}
