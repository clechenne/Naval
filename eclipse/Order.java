package models;

import java.util.Date;

import siena.Column;
import siena.Id;
import siena.Model;
import siena.Query;

public class Order extends Model {
	@Id
    public Long id;

	@Column("ship")
	public Ship ship;

	public Date created;
    
    static Query<Order> all() {
        return Model.all(Order.class);
    }
    
    public static Order findById(Long id) {
        return all().filter("id", id).get();
    }
    
    public Order() {
        super();
    }
       
	public String toString() {
        return "order-"+id;
    }
}
