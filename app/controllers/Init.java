package controllers;

import models.Game;
import models.Ship;
import models.User;
import play.mvc.Before;

public class Init extends Application {
	@Before
	static void checkConnected() {
		if (Auth.getUser() == null) {
			Application.login();
		} else {
			renderArgs.put("user", Auth.getEmail());
		}
	}

	public static void index() {
		User user = getUser();
		
		Game game = new Game("Attu");
		game.turn = 1;
		game.hour = 13;
		game.min = 30;
		
		game.insert();
		
		Ship s = new Ship("Idaho");
		s.user = getUser();
		
		s.game = game;
		s.type = "BB";
		
		s.insert();
		
		render(user, s);
	}
		
    static User getUser() {
		return User.findByEmail(Auth.getEmail());
    }
    
}
