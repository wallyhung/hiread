package com.wally.hiread.dao.order;

import com.wally.hiread.model.order.ShoppingCart;
import com.wally.hiread.model.order.ShoppingCartExample;
import java.util.List;

public interface ShoppingCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int countByExample(ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int insert(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int insertSelective(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    ShoppingCart selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int updateByPrimaryKeySelective(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_shopping_cart
     *
     * @mbggenerated Thu Jun 08 13:46:38 CST 2017
     */
    int updateByPrimaryKey(ShoppingCart record);
}