package com.learning.controller;

import com.learning.model.*;
import com.learning.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class CheckoutController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @Autowired
    InvoicesService invoicesService;

    @Autowired
    InvoicesDetailService invoicesDetailService;

    @Autowired
    ProductService productService;

    @GetMapping ("/checkout")
    public String checkoutPage(Model model, HttpSession session){
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        System.out.println( "Ten thanh toan la:  "  + userDetails.getUsername());
        Map<Integer,Integer> quantity = new HashMap<>();
        List<Product> listsp = new ArrayList<>();
        List<Cart> listCarts = cartService.getListCartProductByUserName(userDetails.getUsername());
        for(Cart c: listCarts)
        {
            listsp.add(c.getProduct());
            quantity.put(c.getProduct().getProduct_id(), c.getQuantity());
        }
        model.addAttribute("cart", listsp);
        model.addAttribute("quantity", quantity);
        model.addAttribute("userinfo", userService.findByUsername(userDetails.getUsername()));
//        model.addAttribute("donhang", new Invoices());


        return "user/checkout";
    }
    @PostMapping(value="/thankyou")
    public String thankyouPage(Model model, HttpSession session, @Param("address")String address,
                               @Param("receiver") String receiver, @Param("phone")String phone
    ){
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        User ngmua = userService.findByUsername(userDetails.getUsername());
        //save invoice
        Invoices donhang = new Invoices();
        donhang.setAddress(address);
        donhang.setStatus(false);
        donhang.setUsername(ngmua);
        donhang.setReceiver(receiver);
        Invoices d = invoicesService.save(donhang);

        //invoice Detail
        List<InvoiceDetail> listDetailDH = new ArrayList<>();
        List<Cart> listCart  = cartService.getListCartProductByUserName(userDetails.getUsername());
        double amount = 0;
        for(Cart c: listCart)
        {
            amount += c.getAmountMoney();
        }
        for(Cart c: listCart)
        {
            InvoiceDetail detailDH = new InvoiceDetail();
            detailDH.setProduct(c.getProduct());
            detailDH.setInvoice_id(d); // hop ly
            detailDH.setQuantity(c.getQuantity());
            detailDH.setTimeorder(new Date());
            detailDH.setPhone(phone); //param
            detailDH.setAmount(amount);

            //giam hang ton
            Optional<Product> productOptional = productService.findById(c.getProduct().getProduct_id());
            Product product = productOptional.orElse(null);
            System.out.println( "Quantiy truoc khi giam : "  + product.getQuantity());
            int saugiam = product.getQuantity() - c.getQuantity();
            System.out.println( "Quantiy sau khi giam : "  + c.getQuantity());
            System.out.println( "Quantiy sau khi giam : "  + saugiam);
            product.setQuantity(saugiam);

            //cleanUpAfterCheckOut
            cartService.deleteCartByProductIdByUserName(c.getProduct().getProduct_id(),userDetails.getUsername() );

            invoicesDetailService.save(detailDH);
            System.out.println( "Da them Chi Toan Hoa Don  ");
        }
        return "user/thankyou";
    }

}