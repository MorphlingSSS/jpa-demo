package site.fish.jpa.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Description: [MyOrder Po]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 10:05
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder extends BaseEntity{
    private String code;
    private Long cId;
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "cId", insertable = false, updatable = false)
    private Customer customer;

    @Override
    public String toString() {
        return "MyOrder{" +
                "id=" + getId() +
                ", code='" + code + '\'' +
                ", cId=" + cId +
                ", total=" + total +
                ", customer=" + customer +
                '}';
    }
}
