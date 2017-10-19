package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.UserRead;
import com.wally.hiread.model.product.UserReadExample;
import java.util.List;

public interface UserReadMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int countByExample(UserReadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int insert(UserRead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int insertSelective(UserRead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    List<UserRead> selectByExample(UserReadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    UserRead selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int updateByPrimaryKeySelective(UserRead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read
     *
     * @mbggenerated Mon Jun 26 10:18:11 CST 2017
     */
    int updateByPrimaryKey(UserRead record);
}