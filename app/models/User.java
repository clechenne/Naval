package models;

import java.util.Date;
import java.util.List;

import play.Play;
import play.data.validation.Required;
import play.libs.Codec;

import siena.Id;
import siena.Index;
import siena.Model;
import siena.Query;

public class User extends Model {
	@Id
    public Long id;
	
	public String email;
	public String name;
	public Date created;
    public Date modified;
    
    @Required
    public String passwordHash;

    
    @Index("game_index")
    public Game game;
    
    public boolean isAdmin() {
        return email.equals(Play.configuration.getProperty("application.adminEmail", ""));
    }
    
    static Query<User> all() {
        return Model.all(User.class);
    }
    
    public static List<User> getAlls() {
    	return User.all().fetch();
    } 
    
    public static int count() {
    	return User.all().count();
    }
    
    public static User findById(Long id) {
        return all().filter("id", id).get();
    }
 
    public static User findByEmail(String email) {
        return all().filter("email", email).get();
    }
    
    public User(String email, String password, String name) {
    	this.email = email;
        this.passwordHash = Codec.hexMD5(password);
        this.name = name;
    }
    
    public boolean checkPassword(String password) {
        return passwordHash.equals(Codec.hexMD5(password));
    }
    
    public User(String email) {
        this.email = email;
    }
    
	public String toString() {
        return name;
    }
}
