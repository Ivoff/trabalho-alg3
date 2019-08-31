package controller;

import forum.game.domain.dao.ICommentUserGameDAO;
import forum.game.domain.dao.IGameDAO;
import forum.game.domain.dao.IUserDAO;
import forum.games.domain.dao.postgres.CommentUserGameDaoImpl;
import forum.games.domain.dao.postgres.GameDaoImpl;
import forum.games.domain.dao.postgres.UserDaoImpl;
import forum.games.domain.entities.CommentUserGame;
import forum.games.domain.entities.Game;
import forum.games.domain.entities.User;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/comments")
public class CommentUserGameController {
    private ICommentUserGameDAO dataBase = new CommentUserGameDaoImpl();
    private IUserDAO userBase = new UserDaoImpl();
    private IGameDAO gameBase = new GameDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentUserGame> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentUserGame select(@PathParam("id")int pk){
        for(CommentUserGame element : dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET
    @Path("/create/{poster_id}/{game_id}/{content}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("poster_id")int poster_id, @PathParam("game_id")int game_id, @PathParam("content")String content){
        try{
            CommentUserGame aux = new CommentUserGame();
            
            for(User element : userBase.show()){
                if(element.getId() == poster_id){
                    aux.setPoster(element);
                    break;
                }
            }
            
            for(Game element : gameBase.show()){
                if(element.getId() == game_id){
                    aux.setGame(element);
                }
            }
            
            aux.setContent(content);                        
            
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
