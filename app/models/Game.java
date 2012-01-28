package models;

import java.util.Date;

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
    
    static Query<Game> all() {
        return Model.all(Game.class);
    }
    
    public static Game findById(Long id) {
        return all().filter("id", id).get();
    }
 
    
    public Game() {
        super();
    }
    
    public Game(String name) {
        this.name = name;
    }
    
	public String toString() {
        return name;
    }
}
