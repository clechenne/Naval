package models;

import java.util.List;

import siena.Filter;
import siena.Id;
import siena.Model;
import siena.Query;

public class Game extends Model {
	@Id
    public Long id;
	
	public String name;
    public int turn;
    public int hour;
    public int min;
    
    @Filter("game")
    public Query<User> users;
    
    static Query<Game> all() {
        return Model.all(Game.class);
    }
    
    public static Game findById(Long id) {
        return all().filter("id", id).get();
    }
    
    public static List<Game> getAlls() {
    	return Game.all().fetch();
    }    

    
    public Game() {
        super();
    }
    
    public Game(String name, int turn, int hour, int min) {
        this.name = name;
        this.turn = turn;
        this.hour = hour;
        this.min = min;
    }
    
	public String toString() {
        return name;
    }
}
