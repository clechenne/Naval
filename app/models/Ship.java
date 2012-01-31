package models;

import java.util.List;

import siena.Column;
import siena.Id;
import siena.Model;
import siena.Query;

public class Ship extends Model {
	@Id
    public Long id;
	
	public User user;
	
	public Game game;
	
	public String name;
    
	public String type;
	
	public double x;
	
	public double y;
	
	public int cap;
	
	public int speed;
	
	public int maxSpeed;
	
    static Query<Ship> all() {
        return Model.all(Ship.class);
    }
    
    public static Ship findById(Long id) {
        return all().filter("id", id).get();
    }
    
    public static List<Ship> findByUser(User user) {
        return all().filter("user", user).fetch();
    }
    
    public Ship() {
        super();
    }
    
    public Ship(String name) {
        this.name = name;
    }
    
	public String toString() {
        return name;
    }
}
