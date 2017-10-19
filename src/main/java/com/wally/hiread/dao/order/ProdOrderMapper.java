package com.wally.hiread.dao.order;

import com.wally.hiread.model.order.ProdOrder;
import com.wally.hiread.model.order.ProdOrderExample;
import java.util.List;

public interface ProdOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int countByExample(ProdOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int insert(ProdOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int insertSelective(ProdOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	List<ProdOrder> selectByExample(ProdOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	ProdOrder selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int updateByPrimaryKeySelective(ProdOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_prod_order
	 * @mbggenerated  Thu Jun 22 10:12:21 CST 2017
	 */
	int updateByPrimaryKey(ProdOrder record);
}