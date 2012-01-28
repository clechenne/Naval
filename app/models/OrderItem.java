package models;

import java.util.Date;
import java.util.List;

import siena.Id;
import siena.Model;
import siena.Query;

public class OrderItem extends Model {
	@Id
    public Long id;
	
	public String name;
	public Date created;
	public Long shipId;
	public int rotation;
	public int acc;
	
    static Query<OrderItem> all() {
        return Model.all(OrderItem.class);
    }
        
    public static OrderItem findByShipId(Long id) {
        return all().filter("shipId", id).get();
    }
    
    public static OrderItem findById(Long id) {
        return all().filter("id", id).get();
    }
    
    public OrderItem() {
        super();
    }
    
	public String toString() {
        return name;
    }
}
