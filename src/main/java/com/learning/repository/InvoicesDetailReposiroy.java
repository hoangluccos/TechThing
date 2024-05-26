package com.learning.repository;

import com.learning.model.InvoiceDetail;
import com.learning.model.Invoices;
import com.learning.service.InvoicesDetailService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesDetailReposiroy extends JpaRepository<InvoiceDetail, Integer> {
    @Query("SELECT i FROM InvoiceDetail i WHERE i.invoice_id.invoice_id = :invoiceID")
    public List<InvoiceDetail> findByInvoiceID(Integer invoiceID);
    @Query("SELECT i FROM InvoiceDetail i") // Sử dụng tên thực thể (entity) thay vì tên bảng
    public List<InvoiceDetail> findAll();
}
