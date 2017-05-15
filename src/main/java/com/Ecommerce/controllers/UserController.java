package com.Ecommerce.controllers;

import com.Ecommerce.domain.*;
import com.Ecommerce.domain.security.Role;
import com.Ecommerce.services.*;
import com.Ecommerce.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

/**
 * Created by Ovi .
 */

@Controller
public class UserController {

    private UserService userService;
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private SecurityService securityService;


    private Logger LOGGER = LoggerFactory.getLogger("UserController");


    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }

    @RequestMapping(value = "/user/show/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Integer id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);

        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getById(id);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }

        model.addAttribute("username", username);

        model.addAttribute("user", user);
        return "user/userform";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/userform";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/userform";
        }

        if (user.getRoles().isEmpty()) {
            user.getRoles().add(new Role());
        } else if (!user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                user.addRole(role);
            }
        }
        user.getCart().setUser(user);

        userService.saveOrUpdate(user);
        securityService.autologin(user.getUsername(), user.getPassword());

        return "redirect:/index";

    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        LOGGER.info("DELETE ID " + id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        for (Role role : user.getRoles()) {
            if (!role.getRole().equals("ADMIN")) {
                return "redirect:/login";
            }
        }
        userService.delete(id);
        String refered = httpServletRequest.getHeader("Referer");
        return "redirect:" + refered;
    }


    //User flow controller
    @RequestMapping(value = "/user/show-account/{username}", method = RequestMethod.GET)
    public String getUserAccount(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("user", userService.findByUsername(username));
        return "user/show";
    }



    //User flow controller
    @RequestMapping(value = "/user/next-step", method = RequestMethod.POST)
    public String nextStep(Cart cart,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }

        AddressShipping addressShipping = user.getCustomer().getShippingAddress();
        if (addressShipping == null) {
            addressShipping = new AddressShipping();
        }


        user.setCart(cart);
        model.addAttribute("username", username);
        model.addAttribute("addressShipping", addressShipping);
        model.addAttribute("user", user);

        userService.saveOrUpdate(user);

        return "user/shipaddressform";
    }




    //User flow controller
    @RequestMapping(value = "/user/finalizeOrder", method = RequestMethod.POST)
    public String finalizeOrder(AddressShipping addressShipping) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);


        Order order = orderService.finalizeOrder(user,addressShipping);

        orderService.saveOrUpdate(order);
        userService.saveOrUpdate(user);

        return "redirect:/order/show/"+order.getId();
    }
}
