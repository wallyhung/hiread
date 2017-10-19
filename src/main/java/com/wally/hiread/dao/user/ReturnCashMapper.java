package com.wally.hiread.dao.user;

import com.wally.hiread.model.user.ReturnCash;
import com.wally.hiread.model.user.ReturnCashExample;
import java.util.List;

public interface ReturnCashMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int countByExample(ReturnCashExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int insert(ReturnCash record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int insertSelective(ReturnCash record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	List<ReturnCash> selectByExample(ReturnCashExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	ReturnCash selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int updateByPrimaryKeySelective(ReturnCash record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_return_cash
	 * @mbggenerated  Tue Jun 06 16:04:48 CST 2017
	 */
	int updateByPrimaryKey(ReturnCash record);
}