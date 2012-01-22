package models;

import java.util.Date;

import siena.Id;
import siena.Model;
import siena.Query;

public class User extends Model {
	@Id
    public Long id;
	
	public String email;
	public String name;
	public Date created;
    public Date modified;
    
    static Query<User> all() {
        return Model.all(User.class);
    }
    
    public static User findById(Long id) {
        return all().filter("id", id).get();
    }
 
    public static User findByEmail(String email) {
        return all().filter("email", email).get();
    }
    
    public User() {
        super();
    }
    
    public User(String email) {
        this.email = email;
    }
    
	public String toString() {
        return name;
    }
}
