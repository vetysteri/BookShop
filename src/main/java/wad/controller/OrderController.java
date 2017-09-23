/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Order;
import wad.service.BookService;
import wad.service.OrderService;

/**
 *
 * @author veke
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private OrderService orderService; 
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
     model.addAttribute("order",orderService.getOrders());
     return "order";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(@RequestParam String name, @RequestParam String address, @RequestParam String postNumber,
            @RequestParam String city, Model model){
        Order order = orderService.placeOrder(name, address, postNumber, city);
        model.addAttribute("order",order);
     //   model.addAttribute("orderbooks",order.getOrderBooks());
        return "order";
    }
    
    
}
