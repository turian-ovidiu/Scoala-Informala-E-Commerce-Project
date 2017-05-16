package com.Ecommerce.controllers;


import com.Ecommerce.domain.Customer;

import com.Ecommerce.domain.Product;
import com.Ecommerce.services.CustomerService;
import com.Ecommerce.services.OrderDetailService;
import com.Ecommerce.services.OrderService;
import com.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
/**
 * Created by Turian Ovidiu.
 * This class represent Stats Controller.
 */

@Controller
public class StatsController {

    private OrderDetailService orderDetailService;

    @Autowired
    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/stats",method = RequestMethod.GET)
    public String statsOrders(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);


        model.addAttribute("countProducts",productService.countAll());
        model.addAttribute("countCustomers", customerService.countAll());
        model.addAttribute("countOrders" , orderService.countAll());
        model.addAttribute("minOrder",orderService.findMin());
        model.addAttribute("maxOrder",orderService.findMax());
        model.addAttribute("averageOrder",orderService.findAverage());


        List<Product> productList = (List<Product>) productService.listAll();
        HashMap<Product,Integer> hashMap = new HashMap<>();

        for (Product product: productList){
            Integer tempInt;
            tempInt = orderDetailService.findCountByProduct(product);
            hashMap.put(product,tempInt);
        }

        Product min = null;
        int minValue = Integer.MAX_VALUE;
        Product max = null;
        int maxValue = 0;
        for (Product key : hashMap.keySet()){
            int value = hashMap.get(key);

            if (value < minValue){
                minValue = value;
                min = key;
            }

            if (value > maxValue){
                maxValue = value;
                max = key;
            }
        }

        model.addAttribute("leastBought",min);
        model.addAttribute("mostBought",max);


        return "stats/list";
    }



    @RequestMapping(value = "/stats/show/{id}", method = RequestMethod.GET)
    public String getStats(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);



        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        model.addAttribute("avgCustomer",orderService.findAverageByCustomer(customer));
        model.addAttribute("minCustomer",orderService.findMinimumByCustomer(customer));
        model.addAttribute("maxCustomer",orderService.findMaximumByCustomer(customer));
        model.addAttribute("countCustomer",orderService.findCountByCustomer(customer));


        return "stats/show";
    }
}
