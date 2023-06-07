package com.cg.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal balance;

    private String address;

    @OneToMany
    private List<Deposit> deposits;

    @OneToMany
    private List<Withdraw> withdraws;

    @OneToMany
    private List<Transfer> senders;

    @OneToMany
    private List<Transfer> recipients;


    public Customer() {
    }

    public Customer(Long id, String fullName, String email, String phone, BigDecimal balance, String address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        String fullName = customer.fullName;
        String address = customer.address;

        if (fullName == null) {
            errors.rejectValue("fullName", "fullName.null", "Họ tên là bắt buộc");
        }
        else {
            if (fullName.trim().length() == 0) {
                errors.rejectValue("fullName", "fullName.empty", "Họ tên là không được rỗng");
            }
            else {
                if (fullName.trim().length() > 20) {
                    errors.rejectValue("fullName", "fullName.length.max", "Họ tên không được quá 20 ký tự");
                }
                if (fullName.trim().length() < 5) {
                    errors.rejectValue("fullName", "fullName.length.min", "Họ tên không được nhỏ hơn 5 ký tự");
                }
            }
        }

        if (address == null) {
            errors.rejectValue("address", "address.null", "Địa chỉ là bắt buộc");
        }
        else {
            if (address.trim().length() == 0) {
                errors.rejectValue("address", "address.empty", "Địa chỉ là không được rỗng");
            }
            else {
                if (address.trim().length() > 20) {
                    errors.rejectValue("address", "address.length.max", "Địa chỉ không được quá 20 ký tự");
                }
                if (address.trim().length() < 5) {
                    errors.rejectValue("address", "address.length.min", "Địa chỉ không được nhỏ hơn 5 ký tự");
                }
            }
        }
    }
}
