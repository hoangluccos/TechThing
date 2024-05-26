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
    @GetMapping("/admin/thong-ke")
    public String thongKe(Model model) {
        List<InvoiceDetail> hoaDons = invoicesDetailService.findAll();
        // Tính toán tổng doanh thu từ các hóa đơn
        double tongDoanhThu = calculateTotalRevenue(hoaDons);

        // Đưa dữ liệu vào model để sử dụng trong HTML
        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("tongDoanhThu", tongDoanhThu);

        // Trả về tên của trang HTML để hiển thị biểu đồ doanh thu
        return "admin/thong-ke";
    }
    private double calculateTotalRevenue(List<InvoiceDetail> hoaDons) {

        double totalRevenue = 0.0;

        // Duyệt qua từng hóa đơn trong danh sách và cộng tổng số tiền từ mỗi hóa đơn vào biến tổng
        for (InvoiceDetail hoaDon : hoaDons) {
            totalRevenue += hoaDon.getAmount(); // Giả sử bạn có một phương thức getter để lấy tổng tiền từ mỗi hóa đơn
        }

        return totalRevenue;
    }
}
