package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.LearningProgress;
import com.wally.hiread.model.product.LearningProgressExample;
import java.util.List;

public interface LearningProgressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int countByExample(LearningProgressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int insert(LearningProgress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int insertSelective(LearningProgress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    List<LearningProgress> selectByExample(LearningProgressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    LearningProgress selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int updateByPrimaryKeySelective(LearningProgress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_learning_progress
     *
     * @mbggenerated Mon May 22 16:30:14 CST 2017
     */
    int updateByPrimaryKey(LearningProgress record);
}