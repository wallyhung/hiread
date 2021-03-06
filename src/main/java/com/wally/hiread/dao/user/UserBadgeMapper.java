package com.wally.hiread.dao.user;

import com.wally.hiread.model.user.UserBadge;
import com.wally.hiread.model.user.UserBadgeExample;
import java.util.List;

public interface UserBadgeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int countByExample(UserBadgeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int insert(UserBadge record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int insertSelective(UserBadge record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	List<UserBadge> selectByExample(UserBadgeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	UserBadge selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int updateByPrimaryKeySelective(UserBadge record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_user_badges
	 * @mbggenerated  Mon Sep 18 11:09:52 CST 2017
	 */
	int updateByPrimaryKey(UserBadge record);


}