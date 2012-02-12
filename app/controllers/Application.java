package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    @Before
    static void globals() {
        renderArgs.put("connected", connectedUser());
    }
    
    @Before
    static void checkSecure() {
        Secure secure = getActionAnnotation(Secure.class);
        if (secure != null) {
            if (connectedUser() == null || (secure.admin() && !connectedUser().isAdmin())) {
                forbidden();
            }
        }
    }
    
    public static void authenticate(String email, String password) {
        User user = User.findByEmail(email);
        if (user == null || !user.checkPassword(password)) {
            flash.error("Bad email or bad password");
            flash.put("email", email);
            login();
        }
        connect(user);
        flash.success("Welcome back %s !", user.name);
        
        if (connectedUser().isAdmin()) {
        	Admin.index();
        } else {
        	Games.index();
        }
    }
    
    public static void login() {
        render();
    }
    
    public static void logout() {
        flash.success("You've been logged out");
        session.clear();
        Application.login();
    }
    
    // ~~~~~~~~~~~~ Some utils
    
    static void connect(User user) {
        session.put("logged", user.id);
    }

    static User connectedUser() {
        String userId = session.get("logged");
        return userId == null ? null : (User) User.findById(Long.parseLong(userId));
    }
}