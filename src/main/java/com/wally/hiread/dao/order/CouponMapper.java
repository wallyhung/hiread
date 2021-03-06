package com.wally.hiread.dao.order;

import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.order.CouponExample;
import java.util.List;

public interface CouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int countByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int insert(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int insertSelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    List<Coupon> selectByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    Coupon selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int updateByPrimaryKeySelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_coupon
     *
     * @mbggenerated Thu Jun 08 14:06:55 CST 2017
     */
    int updateByPrimaryKey(Coupon record);
}