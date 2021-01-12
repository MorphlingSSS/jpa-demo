package site.fish.jpa.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Description: [SpecificationFactory]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 12:16
 */
@Component
public class SpecificationFactory<T> {

    /**
     * Description: 某字段的值等于value的查询条件
     * @author    : Morphling
     * @date      : 2021/1/7 12:29
     * @param attribute : attribute
     * @param value : value
     * @return    : org.springframework.data.jpa.domain.Specification<T>
     */
    public Specification<T> equal(String attribute, Object value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute) , value);
    }

    /**SS
    * Description: 模糊查询，匹配对应字段
    * @author    : Morphling
    * @date      : 2021/1/7 12:27
    * @param attribute : attribute
    * @param value : value
    * @return    : org.springframework.data.jpa.domain.Specification<T>
    */
    public Specification<T> containsLike(String attribute, String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(attribute),"%" + value + "%");
    }

    /**
    * Description: 获取对应属性的值所在区间
    * @author    : Morphling
    * @date      : 2021/1/7 12:31
    * @param attribute : attribute
    * @param min : min
    * @param max : max
    * @return    : org.springframework.data.jpa.domain.Specification<T>
    */
    public Specification<T> between(String attribute, int min,int max){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(attribute),min,max);
    }

    public Specification<T> between(String attribute, double min,double max){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(attribute),min,max);
    }

    /**
    * Description: 通过属性名和集合实现in查询
    * @author    : Morphling
    * @date      : 2021/1/7 12:34
    * @param attribute : attribute
    * @param c : c
    * @return    : org.springframework.data.jpa.domain.Specification<T>
    */
    public Specification<T> in(String attribute, Collection<?> c){
        return (root, criteriaQuery, criteriaBuilder) -> root.get(attribute).in(c);
    }
}
