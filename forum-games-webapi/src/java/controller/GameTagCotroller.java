package controller;

import forum.game.domain.dao.IGameDAO;
import forum.game.domain.dao.IGameTagDAO;
import forum.game.domain.dao.ITagDAO;
import forum.games.domain.dao.postgres.GameDaoImpl;
import forum.games.domain.dao.postgres.GameTagDaoImpl;
import forum.games.domain.dao.postgres.TagDaoImpl;
import forum.games.domain.entities.Game;
import forum.games.domain.entities.GameTag;
import forum.games.domain.entities.Tag;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("game_tag")
public class GameTagCotroller {
    private IGameTagDAO dataBase = new GameTagDaoImpl();
    private IGameDAO gameBase = new GameDaoImpl();
    private ITagDAO tagBase = new TagDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameTag> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameTag select(@PathParam("id")int pk){
        for(GameTag element : dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET
    @Path("/create/{id_game}/{id_tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("id_game")int id_game, @PathParam("id_tag")int id_tag){
        try{
            GameTag aux = new GameTag();
            for(Game element : gameBase.show()){
                if(element.getId() == id_game){
                    aux.setGame(element);
                    break;
                }
            }
            for(Tag element : tagBase.show()){
                if(element.getId() == id_tag){
                    aux.setTag(element);
                    break;
                }
            }
            
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
        return "{\"status\": 1}";
    }
}
