package com.Ecommerce.controllers;

import com.Ecommerce.domain.Order;
import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.domain.User;

import com.Ecommerce.domain.enums.OrderStatus;
import com.Ecommerce.services.OrderService;
import com.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

import java.util.Iterator;
import java.util.List;

/**
 * Created by This class represent Order Controller..
 */
@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order/list",method = RequestMethod.GET)
    public String listOrders(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("orders", orderService.listAll());
        return "order/list";
    }

    @RequestMapping(value = "/order/show/{id}",method = RequestMethod.GET)
    public String getOrder(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("order", orderService.getById(id));
        return "order/show";
    }

    @RequestMapping(value = "order/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("order", orderService.getById(id));
        return "order/orderform";
    }

    @RequestMapping(value = "/order/new",method = RequestMethod.GET)
    public String newOrder(Model model){
        model.addAttribute("order", new Order());
        return "order/orderform";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String saveOrUpdateOrder(Order order){

        List<OrderDetail> orderDetails = order.getOrderDetails();
        Iterator<OrderDetail> iterator = orderDetails.iterator();

        if (!order.getOrderDetails().isEmpty()){
            while (iterator.hasNext()){
                OrderDetail orderDetail = iterator.next();
                orderDetail.setOrder(order);
            }
        }

        orderService.saveOrUpdate(order);
        return "redirect:/order/list";
    }

    @RequestMapping(value = "/order/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id,HttpServletRequest httpServletRequest){
        orderService.delete(id);
        String refered = httpServletRequest.getHeader("Referer");
        return "redirect:"+refered;
    }


    @RequestMapping(value = "/order/listID/{id}",method = RequestMethod.GET)
    public String listCustomerOrders(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }

        model.addAttribute("username", username);
        model.addAttribute("orders", orderService.findOrdersByCustomer_IdOrderByDateCreatedDesc(id));
        return "order/list";
    }


    @RequestMapping(value = "/order/status-new",method = RequestMethod.GET)
    public String listOrdersStatusNew( Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (auth != null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("orders", orderService.findOrdersByOrderStatus(OrderStatus.NEW));
        return "order/list";
    }

    @RequestMapping(value = "/order/status-allocated",method = RequestMethod.GET)
    public String listOrdersStatusAllocated( Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);

        model.addAttribute("orders", orderService.findOrdersByOrderStatus(OrderStatus.ALLOCATED));
        return "order/list";
    }

    @RequestMapping(value = "/order/status-shipped",method = RequestMethod.GET)
    public String listOrdersStatusShipped( Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);

        model.addAttribute("orders", orderService.findOrdersByOrderStatus(OrderStatus.SHIPPED));
        return "order/list";
    }


}
