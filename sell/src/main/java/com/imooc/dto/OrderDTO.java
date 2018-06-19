package com.imooc.dto;

import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: vn
 * @Date: 2018/6/11 23:25
 * @Description:
 */
@Data
public class OrderDTO {
    /*订单id*/
    private String orderId;

    /*买家名称*/
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*buyer weixin openId*/
    private String buyerOpenid;

    /*order amount*/
    private BigDecimal orderAmount;

    /*order staus. defult 0:new order*/
    private Integer orderStatus;

    /*pay status. default 0:not pay*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetails;
}
