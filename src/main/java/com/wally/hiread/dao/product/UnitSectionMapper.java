package com.wally.hiread.dao.product;

import com.wally.hiread.model.product.UnitSection;
import com.wally.hiread.model.product.UnitSectionExample;
import java.util.List;

public interface UnitSectionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int countByExample(UnitSectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int insert(UnitSection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int insertSelective(UnitSection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	List<UnitSection> selectByExample(UnitSectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	UnitSection selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int updateByPrimaryKeySelective(UnitSection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_unit_section
	 * @mbggenerated  Mon May 22 16:15:00 CST 2017
	 */
	int updateByPrimaryKey(UnitSection record);
}