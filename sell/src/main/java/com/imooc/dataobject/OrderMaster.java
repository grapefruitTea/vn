package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: vn
 * @Date: 2018/6/1 21:32
 * @Description:订单表实体
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /*订单id*/
    @Id
    private String orderId;

    /*买家名称*/
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerddress;

    /*buyer weixin openId*/
    private String buyerOpenid;

    /*order amount*/
    private BigDecimal orderAmount;

    /*order staus. defult 0:new order*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*pay status. default 0:not pay*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
