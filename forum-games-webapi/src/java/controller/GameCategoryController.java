package controller;

import forum.game.domain.dao.ICategoryDAO;
import forum.game.domain.dao.IGameCategoryDAO;
import forum.game.domain.dao.IGameDAO;
import forum.games.domain.dao.postgres.CategoryDaoImpl;
import forum.games.domain.dao.postgres.GameCategoryDaoImpl;
import forum.games.domain.dao.postgres.GameDaoImpl;
import forum.games.domain.entities.Category;
import forum.games.domain.entities.Game;
import forum.games.domain.entities.GameCategory;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("game_category")
public class GameCategoryController {
    private IGameCategoryDAO dataBase = new GameCategoryDaoImpl();
    private IGameDAO gameBase = new GameDaoImpl();
    private ICategoryDAO categoryBase = new CategoryDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameCategory> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameCategory select(@PathParam("id")int pk){
        for(GameCategory element : dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET
    @Path("/create/{id_game}/{id_category}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("id_game")int id_game, @PathParam("id_category")int id_category){
        try{
            GameCategory aux = new GameCategory();
            for(Game element : gameBase.show()){
                if(element.getId() == id_game){
                    aux.setGame(element);
                    break;
                }
            }
            for(Category element : categoryBase.show()){
                if(element.getId() == id_category){
                    aux.setCategory(element);
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
