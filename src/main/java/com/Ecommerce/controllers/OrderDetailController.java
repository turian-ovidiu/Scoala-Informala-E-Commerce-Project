package com.Ecommerce.controllers;

import com.Ecommerce.domain.OrderDetail;
import com.Ecommerce.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Turian Ovidiu.
 * This class represent Order Detail Controller.
 */
@Controller
public class OrderDetailController {


    private OrderDetailService orderDetailService;

    @Autowired
    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = "/orderDetail/list",method = RequestMethod.GET)
    public String listOrders(Model model){
        model.addAttribute("orderDetails", orderDetailService.listAll());
        return "orderDetail/list";
    }

    @RequestMapping(value = "/orderDetail/show/{id}",method = RequestMethod.GET)
    public String getOrder(@PathVariable Integer id, Model model){
        model.addAttribute("orderDetail", orderDetailService.getById(id));
        return "orderDetail/show";
    }

    @RequestMapping(value = "orderDetail/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("orderDetail", orderDetailService.getById(id));
        return "orderDetail/orderdetailform";
    }

    @RequestMapping(value = "/orderDetail/new",method = RequestMethod.GET)
    public String newOrder(Model model){
        model.addAttribute("orderDetail", new OrderDetail());
        return "orderDetail/orderdetailform";
    }

    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    public String saveOrUpdateOrder(OrderDetail order){
        OrderDetail savedOrderDetail = orderDetailService.saveOrUpdate(order);
        return "redirect:/orderDetail/show/" + savedOrderDetail.getId();
    }

    @RequestMapping(value = "/orderDetail/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id, HttpServletRequest httpServletRequest){
        orderDetailService.delete(id);
        String refered = httpServletRequest.getHeader("Referer");
        return "redirect:"+refered;
    }
}
