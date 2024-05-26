package com.learning.controller;

import com.learning.model.InvoiceDetail;
import com.learning.model.Invoices;
import com.learning.service.InvoicesDetailService;
import com.learning.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticController {
    //controller thong ke

    @Autowired
    InvoicesDetailService invoicesDetailService;
    @Autowired
    InvoicesService invoicesService;
    @GetMapping("/admin/hoa-don")
    public String thongKe(Model model) {
        List<InvoiceDetail> hoaDons = invoicesDetailService.findAll();

        double tongDoanhThu = calculateTotalRevenue(hoaDons);


        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("tongDoanhThu", tongDoanhThu);


        return "admin/hoa-don";
    }
    private double calculateTotalRevenue(List<InvoiceDetail> hoaDons) {

        double totalRevenue = 0.0;

        for (InvoiceDetail hoaDon : hoaDons) {
            totalRevenue += hoaDon.getAmount();
        }

        return totalRevenue;
    }
}
