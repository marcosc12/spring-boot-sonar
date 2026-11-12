package com.example.mvc.service;

import com.example.mvc.entity.Expense;
import com.example.mvc.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl extends AbstractCrudService<Expense> implements ExpenseService {
    public ExpenseServiceImpl(ExpenseRepository repository) {
        super(repository);
    }
}
