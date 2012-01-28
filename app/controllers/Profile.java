package controllers;

import java.util.Date;

import models.User;
import play.mvc.Before;

public class Profile extends Application {
	@Before
	static void checkConnected() {
		if (Auth.getUser() == null) {
			Application.login();
		} else {
			renderArgs.put("user", Auth.getEmail());
		}
	}

	public static void index(String tag) {
		User user = getUser();

		render(user);
	}
	
	public static void edit(Long userId) {
		notFoundIfNull(userId);
		User user = User.findById(userId);
		notFoundIfNull(user);
		checkSelfUser(user);


		String name = params.get("user.name");
		if(null == name || "".equals(name)) {
			render(user);
			return;
		}

		user.name = name;
		user.modified = new Date();
		user.update();

		Games.index();
	}
	
    static User getUser() {
		return User.findByEmail(Auth.getEmail());
    }
    
    static void checkSelfUser(User user) {
		if(!getUser().equals(user)) {
			forbidden();
		}
	}
}
