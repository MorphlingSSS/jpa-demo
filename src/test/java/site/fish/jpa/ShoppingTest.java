package site.fish.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import site.fish.jpa.po.Customer;
import site.fish.jpa.po.MyOrder;
import site.fish.jpa.po.OrderWithCustomer;
import site.fish.jpa.repository.MyOrderRepository;

import javax.persistence.criteria.*;

/**
 * Description: [ShoppingTest]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/8 10:22
 */
@SpringBootTest(classes = JpaDemoApplication.class)
public class ShoppingTest {
    @Autowired
    private MyOrderRepository myOrderRepository;

    @Test
    public void specInnerJoin(){
        Specification<MyOrder> spec = (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<MyOrder> q1 = criteriaBuilder.createQuery(MyOrder.class);
            Join<Customer,MyOrder> myOrderJoin = root.join("customer", JoinType.INNER);
            q1.select(myOrderJoin).where(criteriaBuilder.equal(root.get("cId"),1));
            return q1.getRestriction();
        };
        resultPrint(spec);
    }

    @Test
    public void specJoinWithParam(){
        Specification<MyOrder> spec = (root,criteriaQuery,cb) -> {
            criteriaQuery.select(root.join("customer")).where(
                    cb.equal(root.get("cId"),1),
                    cb.equal(root.get("customer").get("firstName"),"Jack")
            );
            return criteriaQuery.getRestriction();
        };
        resultPrint(spec);
    }

    @Test
    public void specJoinWithIn(){
        Specification<MyOrder> spec = (root,cq,cb) ->{
          cq.select(root.join("customer")).where(
                  cb.equal(root.get("cId"),1),
                  root.get("id").in(1,2,4)
          );
          return cq.getRestriction();
        };
        resultPrint(spec);
    }

    @Test
    public void specLeftJoin(){
        Specification<MyOrder> spec = (root,cq,cb) -> {
          cq.select(root.join("customer",JoinType.LEFT)).where(
                  cb.equal(root.get("cId"),1)
          );
          return cq.getRestriction();
        };
        resultPrint(spec);
    }

    @Test
    public void MultiTableQueryWithCustomerInterface(){
//        myOrderRepository.findByOrderId(1L,PageRequest.of(0, 10, Sort.Direction.DESC, "o.id"))
//                .stream().forEach(o->{
//            System.out.println(o.getId()+":::"+o.getFirstName()+":::"+o.getTotal()+":::"+o.getLastName());
//        });
        OrderWithCustomer o = myOrderRepository.findByOrderId(1L);
        System.out.println(o.getId()+":::"+o.getFirstName()+":::"+o.getTotal()+":::"+o.getLastName());
    }


    private void resultPrint(Specification<MyOrder> spec) {
        //分页查询
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        //查询的分页结果
        Page<MyOrder> page = myOrderRepository.findAll(spec, pageable);
        page.getContent().forEach(System.out::println);
    }
}
