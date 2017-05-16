package com.Ecommerce.starter;

import com.Ecommerce.domain.*;
import com.Ecommerce.domain.enums.OrderStatus;
import com.Ecommerce.domain.security.Role;
import com.Ecommerce.services.OrderService;
import com.Ecommerce.services.ProductService;
import com.Ecommerce.services.RoleService;
import com.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ovi on 5/15/2017.
 */
@Component
public class JPAStarter implements ApplicationListener<ContextRefreshedEvent> {


    private ProductService productService;
    private UserService userService;
    private RoleService roleService;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadSuperUser();

    }


    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);
            AddressShipping addressShipping = new AddressShipping();
            addressShipping.setCityShipping("Cluj-Napoca");
            addressShipping.setStateShipping("Cluj");
            addressShipping.setAddressLine1("Str. Memorandului , nr. 21");
            addressShipping.setZipCodeShipping("43225");
            order.setShipToAddress(addressShipping);


            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);

            });
            order.calculateTotalCost();
            orderService.saveOrUpdate(order);
        });
    }


    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(2));
            cartDetail.setQuantity(2);
            user.getCart().setUser(user);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }


    public void loadUsersAndCustomers() {

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        Role customerRole = new Role();
        customerRole.setRole("CUSTOMER");


        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("user");
        user1.getRoles().add(customerRole);

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Johnlastname");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLineOne("3 Main St Hollywood Avenue");
        customer1.getBillingAddress().setCity("Los Angeles");
        customer1.getBillingAddress().setState("California");
        customer1.getBillingAddress().setZipCode("63452");
        customer1.setEmail("john@hollywod.com");
        customer1.setPhoneNumber("553.236.0101");
        customer1.setShippingAddress(new AddressShipping());
        customer1.getShippingAddress().setAddressLine1("3 Main St Hollywood Avenue");
        customer1.getShippingAddress().setCityShipping("Los Angeles");
        customer1.getShippingAddress().setStateShipping("California");
        customer1.getShippingAddress().setZipCodeShipping("63452");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);


        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        user2.getRoles().add(adminRole);

        Customer customer2 = new Customer();
        customer2.setFirstName("Captain Jack");
        customer2.setLastName("Sparrow");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLineOne("1 Ship in the Caribbean Sea");
        customer2.getBillingAddress().setCity("Caribbean Sea");
        customer2.getBillingAddress().setState("Caribbean");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("sparrow@caribbean.com");
        customer2.setPhoneNumber("305.323.0233");
        customer2.setShippingAddress(new AddressShipping());
        customer2.getShippingAddress().setAddressLine1("1 Ship in the Caribbean Sea");
        customer2.getShippingAddress().setCityShipping("Caribbean Sea");
        customer2.getShippingAddress().setStateShipping("Caribbean");
        customer2.getShippingAddress().setZipCodeShipping("33101");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);


        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("user3");
        user3.getRoles().add(new Role());

        Customer customer3 = new Customer();
        customer3.setFirstName("Michael");
        customer3.setLastName("John");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLineOne("7 Main St");
        customer3.getBillingAddress().setCity("Los Angeles");
        customer3.getBillingAddress().setState("California");
        customer3.getBillingAddress().setZipCode("35401");
        customer3.setEmail("michealF@burnnotice.com");
        customer3.setPhoneNumber("305.783.0101");
        customer3.setShippingAddress(new AddressShipping());
        customer3.getShippingAddress().setAddressLine1("10 Main St");
        customer3.getShippingAddress().setCityShipping("Los Angeles");
        customer3.getShippingAddress().setStateShipping("California");
        customer3.getShippingAddress().setZipCodeShipping("4522");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);


    }

    public void loadSuperUser() {
        Role superUserRole = new Role();
        superUserRole.setRole("SUPERUSER");


        User superuser = new User();
        superuser.setUsername("superuser");
        superuser.setPassword("superuser");
        superuser.getRoles().add(superUserRole);
        userService.saveOrUpdate(superuser);
    }

    public void loadProducts() {

        Product beefBurger = new Product();
        beefBurger.setName("Beef burger");
        beefBurger.setDescription("Ingredients: Beef, tomatoes, onion, iceberg lettuce, mayonnaise, sweet ketchup, butter, cheese.");
        beefBurger.setPrice(12.99);
        beefBurger.setImageUrl("Beef burger.jpg");
        productService.saveOrUpdate(beefBurger);

        Product gorgonzolaBurger = new Product();
        gorgonzolaBurger.setName("Gorgonzola  burger");
        gorgonzolaBurger.setDescription("Ingredients: Beef, gorgonzola sauce, gorgonzola, rucola, caramelized onion, butter.");
        gorgonzolaBurger.setPrice(14.99);
        gorgonzolaBurger.setImageUrl("Gorgonzola  burger.jpg");
        productService.saveOrUpdate(gorgonzolaBurger);

        Product chickenBurger = new Product();
        chickenBurger.setName("Chicken burger");
        chickenBurger.setDescription("Ingredients: chicken, avocado, red onion, tomato, lettuce, rucola, special sauce.");
        chickenBurger.setPrice(11.99);
        chickenBurger.setImageUrl("chicken burger.jpg");
        productService.saveOrUpdate(chickenBurger);

        Product veggieBurger = new Product();
        veggieBurger.setName("Veggie burger");
        veggieBurger.setDescription("Ingredients: couscous patties, greek yogurt, spring onions, tomato, cucumbers, lettuce.");
        veggieBurger.setPrice(13.99);
        veggieBurger.setImageUrl("Veggie burger.jpg");
        productService.saveOrUpdate(veggieBurger);

        Product labskausBurger = new Product();
        labskausBurger.setName("Labskaus burger");
        labskausBurger.setDescription("Ingredients: labskaus, cucumbers, onion, eggs, dill, sauce.");
        labskausBurger.setPrice(15.99);
        labskausBurger.setImageUrl("Labskaus Burger.jpg");
        productService.saveOrUpdate(labskausBurger);

        Product speedyChiliSteakPasta = new Product();
        speedyChiliSteakPasta.setName("Speedy chili steak pasta");
        speedyChiliSteakPasta.setDescription("Ingredients: beef, garlic, chili peppers, soy sauce, chili sauce.");
        speedyChiliSteakPasta.setPrice(21.99);
        speedyChiliSteakPasta.setImageUrl("Speedy chili steak pasta.jpg");
        productService.saveOrUpdate(speedyChiliSteakPasta);

        Product asianBowlWithSalmonPasta = new Product();
        asianBowlWithSalmonPasta.setName("Asian bowl, salmon pasta");
        asianBowlWithSalmonPasta.setDescription("Ingredients: salmon, broccoli, sugar, ginger, garlic, garlic onions.");
        asianBowlWithSalmonPasta.setPrice(24.99);
        asianBowlWithSalmonPasta.setImageUrl("Asian bowl with salmon and sesame.jpg");
        productService.saveOrUpdate(asianBowlWithSalmonPasta);


        //Pizza
        Product filletOfBreadPizza = new Product();
        filletOfBreadPizza.setName("Fillet of bread pizza");
        filletOfBreadPizza.setDescription("Ingredients: garlic, onion, tomtoes, cherry tomatoes, feta, basil, baby spinach.");
        filletOfBreadPizza.setPrice(20.20);
        filletOfBreadPizza.setImageUrl("Fillet of bread pizza.jpg");
        productService.saveOrUpdate(filletOfBreadPizza);

        Product paleoPizza = new Product();
        paleoPizza.setName("Paleo pizza");
        paleoPizza.setDescription("Ingredients: sweet potato, green cabbage, caramelised onions, creem, sage.");
        paleoPizza.setPrice(23.99);
        paleoPizza.setImageUrl("Paleo pizza.jpg");
        productService.saveOrUpdate(paleoPizza);

        Product asparagusPizza = new Product();
        asparagusPizza.setName("Asparagus pizza");
        asparagusPizza.setDescription("Ingredients: asparagus, camembert, parmesan, mozzarella with melting camembert border.");
        asparagusPizza.setPrice(25.99);
        asparagusPizza.setImageUrl("Asparagus pizza.jpg");
        productService.saveOrUpdate(asparagusPizza);

        Product artichokesAndRicottaPizza = new Product();
        artichokesAndRicottaPizza.setName("Artichokes pizza");
        artichokesAndRicottaPizza.setDescription("Ingredients: artichokes, ricotta, ham, salami, parmesan, rocola, chives, basil.");
        artichokesAndRicottaPizza.setPrice(23.78);
        artichokesAndRicottaPizza.setImageUrl("Artichokes and ricotta pizza.jpg");
        productService.saveOrUpdate(artichokesAndRicottaPizza);


        //Sauces
        Product chiliGarlicSauce = new Product();
        chiliGarlicSauce.setName("Chili garlic sauce");
        chiliGarlicSauce.setDescription("Ingredients: chile peppers, garlic, fresh ginger, vinegar, sugar.");
        chiliGarlicSauce.setPrice(4.99);
        chiliGarlicSauce.setImageUrl("Chili Garlic Sauce.jpg");
        productService.saveOrUpdate(chiliGarlicSauce);

        Product spicyBarbecueSauce = new Product();
        spicyBarbecueSauce.setName("Spicy barbecue sauce");
        spicyBarbecueSauce.setDescription("Ingredients: fresh garlic, sweet onion, jalapeno pepper, vinegar, olive oil.");
        spicyBarbecueSauce.setPrice(4.22);
        spicyBarbecueSauce.setImageUrl("Spicy barbecue sauce.jpg");
        productService.saveOrUpdate(spicyBarbecueSauce);

        //Deserts
        Product nutellaCheesecakesDessert = new Product();
        nutellaCheesecakesDessert.setName("Nutella dessert");
        nutellaCheesecakesDessert.setDescription("Ingredients: whole-grain butter biscuits, butter, nougat cream, hazelnuts, fresh cheese, sugar.");
        nutellaCheesecakesDessert.setPrice(15.99);
        nutellaCheesecakesDessert.setImageUrl("Nutella Cheesecakes.jpg");
        productService.saveOrUpdate(nutellaCheesecakesDessert);

        Product appleCheesecakeDessert = new Product();
        appleCheesecakeDessert.setName("Apple cheesecake dessert");
        appleCheesecakeDessert.setDescription("Ingredients : flour, cinnamon, apples, lime juice, olive oil, sugar, cheese, butter, cream");
        appleCheesecakeDessert.setPrice(16.99);
        appleCheesecakeDessert.setImageUrl("Apple-Cheesecake.jpg");
        productService.saveOrUpdate(appleCheesecakeDessert);

        //Drinks
        Product coffee = new Product();
        coffee.setName("Drink: Natural coffee");
        coffee.setDescription("Coffee time, strong flavor.");
        coffee.setPrice(3.99);
        coffee.setImageUrl("coffee.jpg");
        productService.saveOrUpdate(coffee);

        Product lemonade = new Product();
        lemonade.setName("Drink: Lemonade");
        lemonade.setDescription("Fresh lemonade with honey.");
        lemonade.setPrice(7.99);
        lemonade.setImageUrl("lemonade.jpg");
        productService.saveOrUpdate(lemonade);


    }
}
