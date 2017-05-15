package com.Ecommerce.controllers;

import com.Ecommerce.domain.User;
import com.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ovi .
 */
@Controller
public class CartController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //User flow controller
    @RequestMapping(value = "/cart/show", method = RequestMethod.GET)
    public String getUserCart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }

        user.getCart().calculateTotalCost();

        model.addAttribute("username", username);
        model.addAttribute("cart", user.getCart());
        return "cart/show";
    }
}
