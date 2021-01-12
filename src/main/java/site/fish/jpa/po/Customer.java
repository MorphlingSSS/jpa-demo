package site.fish.jpa.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Description: [Customer Po]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/7 10:03
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity{

    private String firstName;
    private String lastName;

    @OneToMany
    @JoinColumn(name = "cId")
    @ToString.Exclude
    @JsonIgnore
    private List<MyOrder> myOrders;
}
