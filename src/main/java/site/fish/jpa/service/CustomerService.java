package site.fish.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish.jpa.po.Customer;
import site.fish.jpa.repository.CustomerRepository;

import java.util.List;

/**
 * Description: [CustomerService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 10:26
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    /**
    * Description: getCustomersByLastName
    * @author    : Morphling
    * @date      : 2021/1/7 10:30
    * @param lastName : lastName
    * @return    : java.util.List<site.fish.jpa.po.Customer>
    */
    public List<Customer> getCustomersByLastName(String lastName){
        return customerRepository.findByLastName("Bauer");
    }

    /**
    * Description: Save Customer
    * @author    : Morphling
    * @date      : 2021/1/11 10:36
    * @param customer : customer
    * @return    : site.fish.jpa.po.Customer
    */
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Transactional(rollbackFor=Exception.class)
    public void saveAndFlush(Long id){
        Customer customer = customerRepository.getOne(id);
        customer.setLastName(customer.getLastName()+"1");
        customerRepository.saveAndFlush(customer);
    }
    
    /**
    * Description: getOne
    * @author    : Morphling
    * @date      : 2021/1/11 10:41
    * @param id : id
    * @return    : site.fish.jpa.po.Customer
    */
    public Customer getOne(Long id){
        return customerRepository.findById(id).orElse(null);
    }
}
