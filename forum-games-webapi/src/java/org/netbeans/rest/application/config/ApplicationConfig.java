/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author zilas
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(controller.CategoryController.class);
        resources.add(controller.CommentUserGameController.class);
        resources.add(controller.DeveloperController.class);
        resources.add(controller.GameCategoryController.class);
        resources.add(controller.GameController.class);
        resources.add(controller.GameTagCotroller.class);
        resources.add(controller.PublisherController.class);
        resources.add(controller.TagController.class);
        resources.add(controller.UserController.class);
    }
    
}
