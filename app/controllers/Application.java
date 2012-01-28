package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
        if(Auth.isLoggedIn()) {
            User joueur = User.findByEmail(Auth.getEmail());
            if(null == joueur) {
            	joueur = new User();
            	joueur.email = Auth.getEmail();
            	joueur.created = new Date();
            	joueur.insert();
                Profile.edit(joueur.id);
                return;
            }
            Games.index();
        }
        render();
    }
 
    public static void login() {
        Auth.login("Application.index");
    }
 
    public static void logout() {
        Auth.logout("Application.index");
    }

}