package com.learning.service;

import com.learning.model.Invoices;

import java.util.List;
import java.util.Optional;

public interface InvoicesService {
    public Invoices save(Invoices entity);
    public Optional<Invoices> findById(Integer integer);
    public void deleteById(Integer integer);
    public void delete(Invoices entity);
    public void deleteAllById(Iterable<? extends Integer> integers);
    public void deleteAll(Iterable<? extends Invoices> entities);
    public void deleteAll();
    public List<Invoices> findByName(String name);
    public List<Invoices> findAll ();
}
