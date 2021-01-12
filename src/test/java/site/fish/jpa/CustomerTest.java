package site.fish.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import site.fish.jpa.po.Customer;
import site.fish.jpa.repository.CustomerRepository;
import site.fish.jpa.repository.CustomerSpecificationExecutor;
import site.fish.jpa.service.CustomerService;
import site.fish.jpa.util.SpecificationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: [CustomerTest]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 10:11
 */
@SpringBootTest(classes = JpaDemoApplication.class)
public class CustomerTest {
//    @Autowired
//    CustomerRepository customerRepository;

    @Autowired
    CustomerSpecificationExecutor customerSpe;

    @Autowired
    CustomerService customerService;

    @Autowired
    SpecificationFactory<Customer> spf;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testFindByLastName() {
        List<Customer> customers = customerService.getCustomersByLastName("Bauer");
        customers.forEach(customer -> System.out.println(customer.getMyOrders().size()));
    }

    @Test
    public void testSpecification() {
        List<String> names = new ArrayList<>();
        names.add("Chloe");
        names.add("Jack");
        Specification<Customer> spe = spf.in("firstName", names)
                .or(spf.containsLike("lastName", "Palmer"));
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "firstName");
//        customerSpe.findAll(spe).forEach(System.out::println);
        Page<Customer> page = customerSpe.findAll(spe, pageable);
        page.getContent().forEach(System.out::println);
    }

    @Test
    public void testAuditWhenInsert() {
        Customer customer = new Customer();
        customer.setFirstName("Test");
        customer.setLastName("BaseEntity");
        System.out.println(customerService.saveCustomer(customer));
    }

    @Test
    public void testAuditWhenUpdateCustomer(){
        customerService.saveAndFlush(1L);
    }
}
