package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.product.ProductExample;

import java.util.List;

public interface ProductMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int countByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int insert(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int insertSelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	List<Product> selectByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	Product selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int updateByPrimaryKeySelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_product
	 * @mbggenerated  Fri Jul 07 11:49:28 CST 2017
	 */
	int updateByPrimaryKey(Product record);
}