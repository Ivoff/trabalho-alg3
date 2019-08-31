package controller;

import forum.game.domain.dao.IUserDAO;
import forum.games.domain.dao.postgres.UserDaoImpl;
import forum.games.domain.entities.User;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserController {
    private IUserDAO dataBase = new UserDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User select(@PathParam("id")int pk){
        for(User element: dataBase.show()){
            if(element.getId() == pk){
                return element;
            }
        }
        return null;
    }
    
    @GET //eu sei que eu nao devia fazer com get mas nao da tempo de aprender algo novo agora, tenho que terminar isso
    @Path("create/{name}/{email}/{raw_pass}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("name")String name, @PathParam("email")String email, @PathParam("raw_pass")String raw_pass){
        try{
            User aux = new User();
            aux.setName(name);
            aux.setEmail(email);
            aux.setPassHash(raw_pass);
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
