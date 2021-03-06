package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.UserReadCom;
import com.wally.hiread.model.product.UserReadComExample;
import java.util.List;

public interface UserReadComMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int countByExample(UserReadComExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int insert(UserReadCom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int insertSelective(UserReadCom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	List<UserReadCom> selectByExample(UserReadComExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	UserReadCom selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int updateByPrimaryKeySelective(UserReadCom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_read_com
	 * @mbggenerated  Mon Jun 26 10:22:38 CST 2017
	 */
	int updateByPrimaryKey(UserReadCom record);
}