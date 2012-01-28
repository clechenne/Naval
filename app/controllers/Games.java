package controllers;

import java.util.List;

import models.Game;
import models.OrderItem;
import models.Ship;
import models.User;
import play.mvc.Before;

public class Games extends Application {
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
		
		List<Ship> ships = Ship.findByUser(user);
		
		if (ships.size() == 0) {
			flash.error("No ship for %s", user.email);
			render();
		} else { 
			Ship ship = ships.get(0);
		
			Game game = Game.findById(ship.game.id);
			
			OrderItem order = OrderItem.findByShipId(ship.id);
			
			notFoundIfNull(game);
			
			render(user, game,  ship, order);
		}
	}
		
    static User getUser() {
		return User.findByEmail(Auth.getEmail());
    }
    
}
