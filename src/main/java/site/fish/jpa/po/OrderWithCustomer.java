package site.fish.jpa.po;

import java.math.BigDecimal;

/**
 * Description: [OrderWithCustomerInterface]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/11 14:45
 */
public interface OrderWithCustomer {
    Long getId();
    BigDecimal getTotal();
    String getFirstName();
    String getLastName();
}
