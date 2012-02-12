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
		if (connectedUser() == null) {
			Application.login();
		}
	}

	public static void index() {
		
		User user = connectedUser();
		
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
		
    static User connectedUser() {
        String userId = session.get("logged");
        return userId == null ? null : (User) User.findById(Long.parseLong(userId));
    }
    
}
