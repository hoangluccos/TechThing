package com.learning.service;

import com.learning.model.Invoices;
import com.learning.repository.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesServiceImpl implements InvoicesService {
    @Autowired
    InvoicesRepository invoicesRepository;

    @Override
    public Invoices save(Invoices entity) {
        return invoicesRepository.save(entity);
    }
    @Override
    public Optional<Invoices> findById(Integer integer) {
        return invoicesRepository.findById(integer);
    }
    @Override
    public void deleteById(Integer integer) {
        invoicesRepository.deleteById(integer);
    }
    @Override
    public void delete(Invoices entity) {
        invoicesRepository.delete(entity);
    }
    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        invoicesRepository.deleteAllById(integers);
    }
    @Override
    public void deleteAll(Iterable<? extends Invoices> entities) {
        invoicesRepository.deleteAll(entities);
    }
    @Override
    public void deleteAll() {
        invoicesRepository.deleteAll();
    }

    @Override
    public List<Invoices> findByName(String name){
        return invoicesRepository.findByName(name);
    }

    @Override
    public List<Invoices> findAll (){
        return invoicesRepository.findAll();
    }

}
