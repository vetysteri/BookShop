/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Order;
import wad.domain.ShoppingCart;
import wad.repository.OrderRepository;

/**
 *
 * @author veke
 */
@Service
public class OrderService {
    
    @Autowired
    private ShoppingCart shoppingCart;
    
    @Autowired
    private OrderRepository orderRepo;

    @Transactional
    public Order placeOrder(String name, String address, String postNumber, String city) {

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);       
        order.setPostNumber(postNumber);
        order.setCity(city);
        order.setTotalPrice(shoppingCart.getPrice());
        shoppingCart.getBooks().entrySet().stream().forEach( i -> {
        order.setBookCount(i.getValue());
        });        
        shoppingCart.clearCart();
        return orderRepo.save(order);
    }
    
    public List <Order> getOrders(){
     return orderRepo.findAll();
    }    
    
}
