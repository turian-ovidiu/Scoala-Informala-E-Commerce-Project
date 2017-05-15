package com.Ecommerce.controllers;

import com.Ecommerce.domain.CartDetail;
import com.Ecommerce.services.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ovi .
 */
@Controller
public class CartDetailController {

    private CartDetailService cartDetailService;

    @Autowired
    public void setCartDetailService(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }


    @RequestMapping(value = "/cartDetail/list",method = RequestMethod.GET)
    public String listCartDetails(Model model){
        model.addAttribute("cartdetails", cartDetailService.listAll());
        return "cartDetail/list";
    }

    @RequestMapping(value = "/cartDetail/show/{id}",method = RequestMethod.GET)
    public String getCartDetail(@PathVariable Integer id, Model model){
        model.addAttribute("cartdetail", cartDetailService.getById(id));
        return "cartDetail/show";
    }

    @RequestMapping(value = "cartDetail/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("cartdetail", cartDetailService.getById(id));
        return "cartDetail/cartdetailform";
    }

    @RequestMapping(value = "/cartDetail/new",method = RequestMethod.GET)
    public String newCartDetail(Model model){
        model.addAttribute("cartdetail", new CartDetail());
        return "cartDetail/cartdetailform";
    }

    @RequestMapping(value = "/cartDetail", method = RequestMethod.POST)
    public String saveOrUpdateCartDetail(CartDetail order){
        CartDetail savedCartDetail = cartDetailService.saveOrUpdate(order);
        return "redirect:/cartDetail/show/" + savedCartDetail.getId();
    }

    @RequestMapping(value = "/cartDetail/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id,HttpServletRequest httpServletRequest){
        cartDetailService.delete(id);
        String refered = httpServletRequest.getHeader("Referer");
        return "redirect:"+refered;

    }
}
