package com.example.mvc.service;

import com.example.mvc.entity.BankAccount;
import com.example.mvc.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl extends AbstractCrudService<BankAccount> implements BankAccountService {
    public BankAccountServiceImpl(BankAccountRepository repository) {
        super(repository);
    }
}
