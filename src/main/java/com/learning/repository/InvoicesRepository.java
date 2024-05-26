package com.learning.repository;

import com.learning.model.Invoices;
import com.learning.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {
    @Query("SELECT i FROM Invoices i WHERE i.username.username = :name")
    public List<Invoices> findByName(@Param("name") String name);

    @Query("SELECT i FROM Invoices i") // Sử dụng tên thực thể (entity) thay vì tên bảng
    public List<Invoices> findAll();
}
