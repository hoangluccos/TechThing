package com.learning.service;

import com.learning.model.InvoiceDetail;

import java.util.List;
import java.util.Optional;

public interface InvoicesDetailService {
    public InvoiceDetail save(InvoiceDetail entity);
    public Optional<InvoiceDetail> findById(Integer integer);
    public boolean existsById(Integer integer);
    public long count();
    public void deleteById(Integer integer);
    public void delete(InvoiceDetail entity);
    public void deleteAllById(Iterable<? extends Integer> integers);
    public void deleteAll(Iterable<? extends InvoiceDetail> entities);
    public void deleteAll();
    public List<InvoiceDetail> findByInvoiceID(Integer id);
    public List<InvoiceDetail> findAll ();
}
