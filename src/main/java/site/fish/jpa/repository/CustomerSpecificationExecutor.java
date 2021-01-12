package site.fish.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import site.fish.jpa.po.Customer;

/**
 * Description: [JpaSpecificationExecutor]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 11:33
 */

public interface CustomerSpecificationExecutor extends JpaRepository<Customer,Long>,
        JpaSpecificationExecutor<Customer> {

}
