package controllers;

import java.util.Date;
import java.util.List;

import models.Game;
import models.Ship;
import models.User;
import play.data.validation.Required;
import play.libs.Codec;
import play.mvc.Before;

public class Admin extends Application {

	@Before
	static void checkConnected() {
		if (connectedUser() == null) {
			Application.login();
		}
	}

	@Secure(admin = true)
	public static void index() {
		List<Game> games = Game.getAlls();
		List<User> users = User.getAlls();
		List<Ship> ships = Ship.getAlls();
		
		render(games, users, ships);
	}

	@Secure(admin = true)
	public static void createGame() {
		render();
	}

	@Secure(admin = true)
	public static void editGame(Long id) {
		Game game = Game.findById(id);
		notFoundIfNull(game);

		render(game);
	}
	
	@Secure(admin = true)
	public static void updateGame(@Required String name, @Required int turn, @Required int hour, @Required int min, Long id) {
		Game game = Game.findById(id);
		notFoundIfNull(game);
		game.name = name;
		game.turn = turn;
		game.hour = hour;
		game.min = min;
		game.update();
		
		flash.success("Game updated");
		index();
	}
	
	@Secure(admin = true)
	public static void createUser() {
		render();
	}
	
	@Secure(admin = true)
	public static void createShip() {
		render();
	}
	
	@Secure(admin = true)
	public static void addUser(@Required String name,@Required String password, @Required String email) {
		User user = new User(email, password, name);
		user.created = new Date();
		user.save();
		flash.success("User created");
		index();
	}
	
	@Secure(admin = true)
	public static void updateShip(@Required String name, @Required String type, Long id) {
		Ship ship = Ship.findById(id);
		notFoundIfNull(ship);
		ship.name = name;
		ship.type = type;
		ship.update();
		
		flash.success("Ship updated");
		index();
	}
	
	@Secure(admin = true)
	public static void updateUser(@Required String name,@Required String password, @Required String email, Long id) {
		User user = User.findById(id);
		notFoundIfNull(user);
		user.name = name;
		user.passwordHash = Codec.hexMD5(password);
		user.email = email;
		user.modified = new Date();
		user.update();
		
		flash.success("User updated");
		index();
	}
	
	@Secure(admin = true)
	public static void editShip(Long id) {
		Ship ship = Ship.findById(id);
		notFoundIfNull(ship);

		render(ship);
	}
	
	@Secure(admin = true)
	public static void editUser(Long id) {
		User user = User.findById(id);
		notFoundIfNull(user);

		render(user);
	}
	
	@Secure(admin = true)
	public static void deleteUser(Long id) {
		User user = User.findById(id);
		notFoundIfNull(user);
		
		user.delete();
		
		flash.success("User deleted");
		
		index();
	}
	
	@Secure(admin = true)
	public static void deleteGame(Long id) {
		Game game = Game.findById(id);
		notFoundIfNull(game);
		
		game.delete();
		
		flash.success("Game deleted");
		
		index();
	}
	
	@Secure(admin = true)
	public static void deleteShip(Long id) {
		Ship ship = Ship.findById(id);
		notFoundIfNull(ship);
		
		ship.delete();
		
		flash.success("Ship deleted");
		
		index();
	}
	
	@Secure(admin = true)
	public static void addGame(@Required String name, @Required int turn, @Required int hour, @Required int min) {

		Game game = new Game(name, turn, hour, min);
		game.save();
		flash.success("Game created");
		index();
	}

	@Secure(admin = true)
	public static void addShip(@Required String name, @Required String type) {

		Ship ship = new Ship();
		ship.name = name;
		ship.type = type;
		
		ship.save();
		
		flash.success("Ship created");
		
		index();
	}
	
	static User connectedUser() {
		String userId = session.get("logged");
		return userId == null ? null : (User) User.findById(Long
				.parseLong(userId));
	}

}
