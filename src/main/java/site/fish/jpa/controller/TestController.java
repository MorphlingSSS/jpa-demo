package site.fish.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish.jpa.po.Customer;
import site.fish.jpa.po.MyOrder;
import site.fish.jpa.repository.MyOrderRepository;
import site.fish.jpa.service.CustomerService;

import java.util.List;

/**
 * Description: [TestController]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/12 14:39
 */
@RestController
public class TestController {
    @Autowired
    private CustomerService service;

    @Autowired
    private MyOrderRepository orderRepository;

    @GetMapping("/getCustomerTest")
    public List<Customer> getCustomerList(){
        return service.getCustomersByLastName("Bauer");
    }

    @GetMapping("/getOrders")
    public Iterable<MyOrder> getOrders(){
        return orderRepository.findAll();
    }
}
