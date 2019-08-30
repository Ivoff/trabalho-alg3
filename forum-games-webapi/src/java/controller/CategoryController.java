package controller;

import forum.game.domain.dao.ICategoryDAO;
import forum.games.domain.dao.postgres.CategoryDaoImpl;
import forum.games.domain.entities.Category;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/category")
public class CategoryController {
    private ICategoryDAO dataBase = new CategoryDaoImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> index(){
        return dataBase.show();
    }
    
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category select(@PathParam("id")int pk){
        for(Category element : dataBase.show()){
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
            Category aux = new Category();
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