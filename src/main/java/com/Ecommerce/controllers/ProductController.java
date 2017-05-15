package com.Ecommerce.controllers;

import com.Ecommerce.domain.Product;
import com.Ecommerce.domain.User;
import com.Ecommerce.domain.security.Role;
import com.Ecommerce.services.ProductService;
import com.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ovi on 5/15/2017.
 */
@Controller
public class ProductController {


    private ProductService productService;
    private UserService userService;


    @Value("${local.files.dir}")
    private String localFilesDir;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAll());
        return "products";
    }

    @RequestMapping(value = "/product/list",method = RequestMethod.GET)
    public String listProducts(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username",username);
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping(value = "/product/show/{id}",method = RequestMethod.GET)
    public String getProduct(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        for (Role role : user.getRoles()) {
            if (!role.getRole().equals("ADMIN")) {
                return "redirect:/login";
            }
        }

        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping(value = "product/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        for (Role role : user.getRoles()) {
            if (!role.getRole().equals("ADMIN")) {
                return "redirect:/login";
            }
        }

        model.addAttribute("product", productService.getById(id));
        return "product/productform";
    }

    @RequestMapping(value = "/product/new",method = RequestMethod.GET)
    public String newProduct(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        for (Role role : user.getRoles()) {
            if (!role.getRole().equals("ADMIN")) {
                return "redirect:/login";
            }
        }
        model.addAttribute("product", new Product());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct( MultipartFile file,Product product) throws IOException {
        File localFile = new File(localFilesDir,System.currentTimeMillis() +"_"+ file.getOriginalFilename());
        file.transferTo(localFile);

        product.setImageUrl(localFile.getName());

        Product savedProduct = productService.saveOrUpdate(product);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping(value = "/product/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id,HttpServletRequest httpServletRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        for (Role role : user.getRoles()) {
            if (!role.getRole().equals("ADMIN")) {
                return "redirect:/login";
            }
        }
        productService.delete(id);
        String refered = httpServletRequest.getHeader("Referer");
        return "redirect:" + refered;
    }


    @RequestMapping(value = "/product/list/burgers",method = RequestMethod.GET)
    public String listBurgers(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Burger"));
        return "product/list";
    }

    @RequestMapping(value = "/product/list/pasta",method = RequestMethod.GET)
    public String listPasta(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Pasta"));
        return "product/list";
    }

    @RequestMapping(value = "/product/list/pizza",method = RequestMethod.GET)
    public String listPizza(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Pizza"));
        return "product/list";
    }

    @RequestMapping(value = "/product/list/sauces",method = RequestMethod.GET)
    public String listSauces(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Sauce"));
        return "product/list";
    }

    @RequestMapping(value = "/product/list/desserts",method = RequestMethod.GET)
    public String listDesserts(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Dessert"));
        return "product/list";
    }

    @RequestMapping(value = "/product/list/drinks",method = RequestMethod.GET)
    public String listDrinks(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user!=null){
            model.addAttribute("countCartProducts",userService.countCartProducts(user));
        }
        model.addAttribute("username",username);
        model.addAttribute("products", productService.findByName("Drink"));
        return "product/list";
    }


    //User flow controller
    @RequestMapping(value = "/product/addProduct/{id}",method = RequestMethod.GET)
    public String addProduct(@PathVariable Integer id, HttpServletRequest httpServletRequest ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findByUsername(username);
        Product product = productService.getById(id);
        productService.addProduct(user,product);
        userService.saveOrUpdate(user);
        String refered = httpServletRequest.getHeader("Referer");

        return "redirect:"+refered;
    }



    @RequestMapping(value ="product/search", method = RequestMethod.POST)
    public String searchProduct(@RequestParam("productName") String productName){

        return "redirect:/product/searchList/"+productName;
    }


    @RequestMapping(value = "/product/searchList/{productName}",method = RequestMethod.GET)
    public String listProductsSearch(@PathVariable String productName,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username",username);

        List<Product> products = productService.findByDescription(productName);


        for (Product product: productService.findByName(productName)){
            if (!products.contains(product)){
                products.add(product);
            }
        }

        model.addAttribute("products",products);
        return "product/list";
    }
}
