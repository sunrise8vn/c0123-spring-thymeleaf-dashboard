package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.Customer;
import com.cg.model.ErrorMessage;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ValidateUtils validateUtils;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Customer customer, BindingResult bindingResult) {

        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã khách hàng không hợp lệ");
        }

        new Customer().validate(customer, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Long customerId = Long.parseLong(id);

        Optional<Customer> customerOptional = customerService.findById(customerId);

        ErrorMessage errorMessage = new ErrorMessage();

        if (!customerOptional.isPresent()) {
//            errorMessage.setMessage("Mã khách hàng không tồn tại");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            throw new DataInputException("Mã khách hàng không tồn tại");
        }

        Boolean existEmail = customerService.existsByEmailAndIdNot(customer.getEmail(), customerId);

        if (existEmail) {
//            errorMessage.setMessage("Email đã tồn tại");
//            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
            throw new EmailExistsException("Email đã tồn tại");
        }

        Customer updateCustomer = customerOptional.get();

        updateCustomer.setFullName(customer.getFullName());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setAddress(customer.getAddress());

        customerService.save(updateCustomer);

        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

}
