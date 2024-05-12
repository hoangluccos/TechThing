package com.learning.service;

import com.learning.model.InvoiceDetail;
import com.learning.model.Invoices;
import com.learning.repository.InvoicesDetailReposiroy;
import com.learning.repository.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesDetailServiceImpl implements InvoicesDetailService{
    @Autowired
    InvoicesDetailReposiroy repo;

    @Override
    public InvoiceDetail save(InvoiceDetail entity) {
        return repo.save(entity);
    }
    @Override
    public Optional<InvoiceDetail> findById(Integer integer) {
        return repo.findById(integer);
    }
    @Override
    public boolean existsById(Integer integer) {
        return repo.existsById(integer);
    }
    @Override
    public long count() {
        return repo.count();
    }
    @Override
    public void deleteById(Integer integer) {
        repo.deleteById(integer);
    }
    @Override
    public void delete(InvoiceDetail entity) {
        repo.delete(entity);
    }
    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        repo.deleteAllById(integers);
    }
    @Override
    public void deleteAll(Iterable<? extends InvoiceDetail> entities) {
        repo.deleteAll(entities);
    }
    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public List<InvoiceDetail> findByInvoiceID(Integer id){
        return repo.findByInvoiceID(id);
    }

    @Override
    public List<InvoiceDetail> findAll (){
        return repo.findAll();
    }
}
