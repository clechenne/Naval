package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.Ship;
import models.User;
import play.mvc.Controller;

public class Init extends Controller {

	public static void index() {
		
		
		User u1 = new User("christophe.lechenne@gmail.com");
		u1.name = "Chris";
		u1.save();
		
		User u2 = new User("serge.lechenne@gmail.com");
		u2.name = "Serge";
		u2.save();
		
		Game game = new Game("Attu");
		game.turn = 1;
		game.hour = 13;
		game.min = 30;
		game.save();
		
		u1.game = game;
		u1.update();
		u2.game = game;
		u2.update();
		
		Ship s = new Ship("Idaho");
		s.user = u1;
		s.game = game;
		s.type = "BB";
		s.x = 12.62;
		s.y = 10.79;
		s.cap = 45;
		s.maxSpeed = 25;
		s.speed = 20;
		s.insert();
		
		s = new Ship("Nagato");
		s.user = u2;
		s.game = game;
		s.type = "BB";
		s.x = 32.65;
		s.y = 24.06;
		s.cap = 220;
		s.maxSpeed = 27;
		s.speed = 22;		
		s.insert();
		
		render();
	}    
}
