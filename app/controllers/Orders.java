package controllers;

import java.util.Date;

import models.OrderItem;
import models.Ship;
import models.User;
import play.mvc.Before;

public class Orders extends Application {
	@Before
	static void checkConnected() {
		if (Auth.getUser() == null) {
			Application.login();
		} else {
			renderArgs.put("user", Auth.getEmail());
		}
	}

	public static void add(Long shipId) {
		int rotation = params.get("rotation", Integer.class);
		
		OrderItem t = new OrderItem();
		t.shipId = shipId;
		t.name = "" + shipId;
		t.created = new Date();
		t.rotation = rotation;
		t.acc = params.get("acc", Integer.class);
		t.insert();
		
		flash.success("Order inserted");
		Games.index();
	}
		
    static User getUser() {
		return User.findByEmail(Auth.getEmail());
    }
}
