package site.fish.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.fish.jpa.po.MyOrder;
import site.fish.jpa.po.OrderWithCustomer;

import java.util.List;

/**
 * Description: [MyOrderRepository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/8 10:19
 */
@Repository
public interface MyOrderRepository extends JpaSpecificationExecutor<MyOrder>, CrudRepository<MyOrder, Long> {
    /**
    * Description: findByOrderId
    * @author    : Morphling
    * @date      : 2021/1/11 15:01
    * @param pageable : pageable
    * @return    : org.springframework.data.domain.Page<site.fish.jpa.po.OrderWithCustomer>
    */
    @Query(value = "SELECT o.id as id,o.total as total , c.first_name as firstName, c.last_name as lastName FROM `my_order` as o  LEFT JOIN customer as c on o.c_id = c.id ",nativeQuery = true)
    Page<OrderWithCustomer> findByOrderId(Pageable pageable);

    /**
    * Description: findByOrderId
    * @author    : Morphling
    * @date      : 2021/1/11 15:22
    * @param id : id
    * @return    : site.fish.jpa.po.OrderWithCustomer
    */
    @Query(value = "SELECT o.id as id,o.total as total , c.first_name as firstName, c.last_name as lastName FROM `my_order` as o  LEFT JOIN customer as c on o.c_id = c.id where o.id=?1",nativeQuery = true)
    OrderWithCustomer findByOrderId(Long id);
}
