package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.Ship;
import models.User;
import play.libs.Codec;
import play.mvc.Controller;

public class Init extends Controller {

	public static void index() {
		
		User u1 = new User("christophe.lechenne@gmail.com");
		u1.name = "Chris";
		u1.passwordHash = Codec.hexMD5("goar@jb");
		u1.save();
		
		render();
	}    
}
