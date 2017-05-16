package com.Ecommerce.controllers;

import com.Ecommerce.domain.Customer;
import com.Ecommerce.domain.User;
import com.Ecommerce.domain.security.Role;
import com.Ecommerce.services.CustomerService;
import com.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Turian Ovidiu.
 * This class represent Customer Controller.
 */
@Controller
public class CustomerController {


    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(value = "customer/list", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        for (Role role : user.getRoles()) {
            if (role.getRole().equals("CUSTOMER")) {
                return "redirect:/login";
            }
        }

        model.addAttribute("username", username);
        model.addAttribute("customers", customerService.listAll());
        return "customer/list";


    }

    @RequestMapping(value = "customer/show/{id}", method = RequestMethod.GET)
    public String showCustomer(@PathVariable Integer id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("username", username);
        model.addAttribute("customer", customerService.getById(id));
        return "customer/show";
    }

    @RequestMapping(value = "customer/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customerform";
    }

    @RequestMapping(value = "customer/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdate(Customer customer) {
        Customer newCustomer = customerService.saveOrUpdate(customer);
        return "redirect:customer/show/" + newCustomer.getId();
    }

    @RequestMapping(value = "customer/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "customer/search", method = RequestMethod.POST)
    public String searchCustomer(@RequestParam("customerName") String customerName, Model model) {

        return "redirect:/customer/searchList/" + customerName;
    }

    @RequestMapping(value = "/customer/searchList/{customerName}", method = RequestMethod.GET)
    public String listCustomersSearch(@PathVariable String customerName, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);

        List<Customer> customers = customerService.findByFirstNameContaining(customerName);


        for (Customer customer : customerService.findByLastNameContaining(customerName)) {
            if (!customers.contains(customer)) {
                customers.add(customer);
            }
        }

        model.addAttribute("customers", customers);
        return "customer/list";
    }
}
