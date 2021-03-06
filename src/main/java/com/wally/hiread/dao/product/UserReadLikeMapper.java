package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.UserReadLike;
import com.wally.hiread.model.product.UserReadLikeExample;
import java.util.List;

public interface UserReadLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int countByExample(UserReadLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int insert(UserReadLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int insertSelective(UserReadLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    List<UserReadLike> selectByExample(UserReadLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    UserReadLike selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int updateByPrimaryKeySelective(UserReadLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_user_read_like
     *
     * @mbggenerated Mon Jun 26 10:20:32 CST 2017
     */
    int updateByPrimaryKey(UserReadLike record);
}