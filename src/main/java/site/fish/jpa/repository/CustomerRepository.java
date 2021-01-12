package site.fish.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.fish.jpa.po.Customer;

import java.util.List;

/**
 * Description: [CustomerRepository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 10:09
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
    * Description: findByLastName
    * @author    : Morphling
    * @date      : 2021/1/7 10:13
    * @param lastName : lastName
    * @return    : java.util.List<site.fish.jpa.po.Customer>
    */
    List<Customer> findByLastName(String lastName);
}
