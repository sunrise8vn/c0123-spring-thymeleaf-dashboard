package com.cg.controller;


import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;


    @GetMapping
    public String showListPage(Model model) {

        List<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);

        return "/customer/list";
    }


    @GetMapping("/deposit/{customerId}")
    public String showListPage(Model model, @PathVariable Long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Thông tin khách hàng không tồn tại");

            return "/customer/deposit";
        }

        Customer customer = customerOptional.get();

        model.addAttribute("customer", customer);
        model.addAttribute("deposit", new Deposit());

        return "/customer/deposit";
    }

    @PostMapping("/deposit/{customerId}")
    public String deposit(@PathVariable Long customerId, @ModelAttribute Deposit deposit, Model model) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Thông tin khách hàng không tồn tại");

            return "/customer/deposit";
        }

        Customer customer = customerOptional.get();

        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal newBalance = currentBalance.add(transactionAmount);
        customer.setBalance(newBalance);
        customerService.save(customer);

        deposit.setCustomer(customer);
        depositService.save(deposit);

        model.addAttribute("customer", customer);
        model.addAttribute("deposit", new Deposit());


        return "/customer/deposit";
    }
}

