package com.wally.hiread.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampainExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public CampainExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDatecreatedIsNull() {
			addCriterion("dateCreated is null");
			return (Criteria) this;
		}

		public Criteria andDatecreatedIsNotNull() {
			addCriterion("dateCreated is not null");
			return (Criteria) this;
		}

		public Criteria andDatecreatedEqualTo(Date value) {
			addCriterion("dateCreated =", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedNotEqualTo(Date value) {
			addCriterion("dateCreated <>", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedGreaterThan(Date value) {
			addCriterion("dateCreated >", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedGreaterThanOrEqualTo(Date value) {
			addCriterion("dateCreated >=", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedLessThan(Date value) {
			addCriterion("dateCreated <", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedLessThanOrEqualTo(Date value) {
			addCriterion("dateCreated <=", value, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedIn(List<Date> values) {
			addCriterion("dateCreated in", values, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedNotIn(List<Date> values) {
			addCriterion("dateCreated not in", values, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedBetween(Date value1, Date value2) {
			addCriterion("dateCreated between", value1, value2, "datecreated");
			return (Criteria) this;
		}

		public Criteria andDatecreatedNotBetween(Date value1, Date value2) {
			addCriterion("dateCreated not between", value1, value2,
					"datecreated");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedIsNull() {
			addCriterion("dateModified is null");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedIsNotNull() {
			addCriterion("dateModified is not null");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedEqualTo(Date value) {
			addCriterion("dateModified =", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedNotEqualTo(Date value) {
			addCriterion("dateModified <>", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedGreaterThan(Date value) {
			addCriterion("dateModified >", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedGreaterThanOrEqualTo(Date value) {
			addCriterion("dateModified >=", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedLessThan(Date value) {
			addCriterion("dateModified <", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedLessThanOrEqualTo(Date value) {
			addCriterion("dateModified <=", value, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedIn(List<Date> values) {
			addCriterion("dateModified in", values, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedNotIn(List<Date> values) {
			addCriterion("dateModified not in", values, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedBetween(Date value1, Date value2) {
			addCriterion("dateModified between", value1, value2, "datemodified");
			return (Criteria) this;
		}

		public Criteria andDatemodifiedNotBetween(Date value1, Date value2) {
			addCriterion("dateModified not between", value1, value2,
					"datemodified");
			return (Criteria) this;
		}

		public Criteria andProductIsNull() {
			addCriterion("c_product is null");
			return (Criteria) this;
		}

		public Criteria andProductIsNotNull() {
			addCriterion("c_product is not null");
			return (Criteria) this;
		}

		public Criteria andProductEqualTo(String value) {
			addCriterion("c_product =", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductNotEqualTo(String value) {
			addCriterion("c_product <>", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductGreaterThan(String value) {
			addCriterion("c_product >", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductGreaterThanOrEqualTo(String value) {
			addCriterion("c_product >=", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductLessThan(String value) {
			addCriterion("c_product <", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductLessThanOrEqualTo(String value) {
			addCriterion("c_product <=", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductLike(String value) {
			addCriterion("c_product like", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductNotLike(String value) {
			addCriterion("c_product not like", value, "product");
			return (Criteria) this;
		}

		public Criteria andProductIn(List<String> values) {
			addCriterion("c_product in", values, "product");
			return (Criteria) this;
		}

		public Criteria andProductNotIn(List<String> values) {
			addCriterion("c_product not in", values, "product");
			return (Criteria) this;
		}

		public Criteria andProductBetween(String value1, String value2) {
			addCriterion("c_product between", value1, value2, "product");
			return (Criteria) this;
		}

		public Criteria andProductNotBetween(String value1, String value2) {
			addCriterion("c_product not between", value1, value2, "product");
			return (Criteria) this;
		}

		public Criteria andCampainTypeIsNull() {
			addCriterion("c_campainType is null");
			return (Criteria) this;
		}

		public Criteria andCampainTypeIsNotNull() {
			addCriterion("c_campainType is not null");
			return (Criteria) this;
		}

		public Criteria andCampainTypeEqualTo(String value) {
			addCriterion("c_campainType =", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeNotEqualTo(String value) {
			addCriterion("c_campainType <>", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeGreaterThan(String value) {
			addCriterion("c_campainType >", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeGreaterThanOrEqualTo(String value) {
			addCriterion("c_campainType >=", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeLessThan(String value) {
			addCriterion("c_campainType <", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeLessThanOrEqualTo(String value) {
			addCriterion("c_campainType <=", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeLike(String value) {
			addCriterion("c_campainType like", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeNotLike(String value) {
			addCriterion("c_campainType not like", value, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeIn(List<String> values) {
			addCriterion("c_campainType in", values, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeNotIn(List<String> values) {
			addCriterion("c_campainType not in", values, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeBetween(String value1, String value2) {
			addCriterion("c_campainType between", value1, value2, "campainType");
			return (Criteria) this;
		}

		public Criteria andCampainTypeNotBetween(String value1, String value2) {
			addCriterion("c_campainType not between", value1, value2,
					"campainType");
			return (Criteria) this;
		}

		public Criteria andEndDateIsNull() {
			addCriterion("c_endDate is null");
			return (Criteria) this;
		}

		public Criteria andEndDateIsNotNull() {
			addCriterion("c_endDate is not null");
			return (Criteria) this;
		}

		public Criteria andEndDateEqualTo(String value) {
			addCriterion("c_endDate =", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateNotEqualTo(String value) {
			addCriterion("c_endDate <>", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateGreaterThan(String value) {
			addCriterion("c_endDate >", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateGreaterThanOrEqualTo(String value) {
			addCriterion("c_endDate >=", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateLessThan(String value) {
			addCriterion("c_endDate <", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateLessThanOrEqualTo(String value) {
			addCriterion("c_endDate <=", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateLike(String value) {
			addCriterion("c_endDate like", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateNotLike(String value) {
			addCriterion("c_endDate not like", value, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateIn(List<String> values) {
			addCriterion("c_endDate in", values, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateNotIn(List<String> values) {
			addCriterion("c_endDate not in", values, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateBetween(String value1, String value2) {
			addCriterion("c_endDate between", value1, value2, "endDate");
			return (Criteria) this;
		}

		public Criteria andEndDateNotBetween(String value1, String value2) {
			addCriterion("c_endDate not between", value1, value2, "endDate");
			return (Criteria) this;
		}

		public Criteria andApplyToAllIsNull() {
			addCriterion("c_applyToAll is null");
			return (Criteria) this;
		}

		public Criteria andApplyToAllIsNotNull() {
			addCriterion("c_applyToAll is not null");
			return (Criteria) this;
		}

		public Criteria andApplyToAllEqualTo(String value) {
			addCriterion("c_applyToAll =", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllNotEqualTo(String value) {
			addCriterion("c_applyToAll <>", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllGreaterThan(String value) {
			addCriterion("c_applyToAll >", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllGreaterThanOrEqualTo(String value) {
			addCriterion("c_applyToAll >=", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllLessThan(String value) {
			addCriterion("c_applyToAll <", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllLessThanOrEqualTo(String value) {
			addCriterion("c_applyToAll <=", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllLike(String value) {
			addCriterion("c_applyToAll like", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllNotLike(String value) {
			addCriterion("c_applyToAll not like", value, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllIn(List<String> values) {
			addCriterion("c_applyToAll in", values, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllNotIn(List<String> values) {
			addCriterion("c_applyToAll not in", values, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllBetween(String value1, String value2) {
			addCriterion("c_applyToAll between", value1, value2, "applyToAll");
			return (Criteria) this;
		}

		public Criteria andApplyToAllNotBetween(String value1, String value2) {
			addCriterion("c_applyToAll not between", value1, value2,
					"applyToAll");
			return (Criteria) this;
		}

		public Criteria andStartDateIsNull() {
			addCriterion("c_startDate is null");
			return (Criteria) this;
		}

		public Criteria andStartDateIsNotNull() {
			addCriterion("c_startDate is not null");
			return (Criteria) this;
		}

		public Criteria andStartDateEqualTo(String value) {
			addCriterion("c_startDate =", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotEqualTo(String value) {
			addCriterion("c_startDate <>", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateGreaterThan(String value) {
			addCriterion("c_startDate >", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateGreaterThanOrEqualTo(String value) {
			addCriterion("c_startDate >=", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateLessThan(String value) {
			addCriterion("c_startDate <", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateLessThanOrEqualTo(String value) {
			addCriterion("c_startDate <=", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateLike(String value) {
			addCriterion("c_startDate like", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotLike(String value) {
			addCriterion("c_startDate not like", value, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateIn(List<String> values) {
			addCriterion("c_startDate in", values, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotIn(List<String> values) {
			addCriterion("c_startDate not in", values, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateBetween(String value1, String value2) {
			addCriterion("c_startDate between", value1, value2, "startDate");
			return (Criteria) this;
		}

		public Criteria andStartDateNotBetween(String value1, String value2) {
			addCriterion("c_startDate not between", value1, value2, "startDate");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIsNull() {
			addCriterion("c_couponAmount is null");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIsNotNull() {
			addCriterion("c_couponAmount is not null");
			return (Criteria) this;
		}

		public Criteria andCouponAmountEqualTo(String value) {
			addCriterion("c_couponAmount =", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotEqualTo(String value) {
			addCriterion("c_couponAmount <>", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountGreaterThan(String value) {
			addCriterion("c_couponAmount >", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountGreaterThanOrEqualTo(String value) {
			addCriterion("c_couponAmount >=", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountLessThan(String value) {
			addCriterion("c_couponAmount <", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountLessThanOrEqualTo(String value) {
			addCriterion("c_couponAmount <=", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountLike(String value) {
			addCriterion("c_couponAmount like", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotLike(String value) {
			addCriterion("c_couponAmount not like", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIn(List<String> values) {
			addCriterion("c_couponAmount in", values, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotIn(List<String> values) {
			addCriterion("c_couponAmount not in", values, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountBetween(String value1, String value2) {
			addCriterion("c_couponAmount between", value1, value2,
					"couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotBetween(String value1, String value2) {
			addCriterion("c_couponAmount not between", value1, value2,
					"couponAmount");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcIsNull() {
			addCriterion("c_campainPicPc is null");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcIsNotNull() {
			addCriterion("c_campainPicPc is not null");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcEqualTo(String value) {
			addCriterion("c_campainPicPc =", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcNotEqualTo(String value) {
			addCriterion("c_campainPicPc <>", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcGreaterThan(String value) {
			addCriterion("c_campainPicPc >", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcGreaterThanOrEqualTo(String value) {
			addCriterion("c_campainPicPc >=", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcLessThan(String value) {
			addCriterion("c_campainPicPc <", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcLessThanOrEqualTo(String value) {
			addCriterion("c_campainPicPc <=", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcLike(String value) {
			addCriterion("c_campainPicPc like", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcNotLike(String value) {
			addCriterion("c_campainPicPc not like", value, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcIn(List<String> values) {
			addCriterion("c_campainPicPc in", values, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcNotIn(List<String> values) {
			addCriterion("c_campainPicPc not in", values, "campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcBetween(String value1, String value2) {
			addCriterion("c_campainPicPc between", value1, value2,
					"campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainPicPcNotBetween(String value1, String value2) {
			addCriterion("c_campainPicPc not between", value1, value2,
					"campainPicPc");
			return (Criteria) this;
		}

		public Criteria andCampainNameIsNull() {
			addCriterion("c_campainName is null");
			return (Criteria) this;
		}

		public Criteria andCampainNameIsNotNull() {
			addCriterion("c_campainName is not null");
			return (Criteria) this;
		}

		public Criteria andCampainNameEqualTo(String value) {
			addCriterion("c_campainName =", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameNotEqualTo(String value) {
			addCriterion("c_campainName <>", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameGreaterThan(String value) {
			addCriterion("c_campainName >", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameGreaterThanOrEqualTo(String value) {
			addCriterion("c_campainName >=", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameLessThan(String value) {
			addCriterion("c_campainName <", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameLessThanOrEqualTo(String value) {
			addCriterion("c_campainName <=", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameLike(String value) {
			addCriterion("c_campainName like", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameNotLike(String value) {
			addCriterion("c_campainName not like", value, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameIn(List<String> values) {
			addCriterion("c_campainName in", values, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameNotIn(List<String> values) {
			addCriterion("c_campainName not in", values, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameBetween(String value1, String value2) {
			addCriterion("c_campainName between", value1, value2, "campainName");
			return (Criteria) this;
		}

		public Criteria andCampainNameNotBetween(String value1, String value2) {
			addCriterion("c_campainName not between", value1, value2,
					"campainName");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileIsNull() {
			addCriterion("c_campainPicMobile is null");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileIsNotNull() {
			addCriterion("c_campainPicMobile is not null");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileEqualTo(String value) {
			addCriterion("c_campainPicMobile =", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileNotEqualTo(String value) {
			addCriterion("c_campainPicMobile <>", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileGreaterThan(String value) {
			addCriterion("c_campainPicMobile >", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileGreaterThanOrEqualTo(String value) {
			addCriterion("c_campainPicMobile >=", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileLessThan(String value) {
			addCriterion("c_campainPicMobile <", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileLessThanOrEqualTo(String value) {
			addCriterion("c_campainPicMobile <=", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileLike(String value) {
			addCriterion("c_campainPicMobile like", value, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileNotLike(String value) {
			addCriterion("c_campainPicMobile not like", value,
					"campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileIn(List<String> values) {
			addCriterion("c_campainPicMobile in", values, "campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileNotIn(List<String> values) {
			addCriterion("c_campainPicMobile not in", values,
					"campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileBetween(String value1, String value2) {
			addCriterion("c_campainPicMobile between", value1, value2,
					"campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCampainPicMobileNotBetween(String value1,
				String value2) {
			addCriterion("c_campainPicMobile not between", value1, value2,
					"campainPicMobile");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateIsNull() {
			addCriterion("c_couponExpiryDate is null");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateIsNotNull() {
			addCriterion("c_couponExpiryDate is not null");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateEqualTo(String value) {
			addCriterion("c_couponExpiryDate =", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateNotEqualTo(String value) {
			addCriterion("c_couponExpiryDate <>", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateGreaterThan(String value) {
			addCriterion("c_couponExpiryDate >", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateGreaterThanOrEqualTo(String value) {
			addCriterion("c_couponExpiryDate >=", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateLessThan(String value) {
			addCriterion("c_couponExpiryDate <", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateLessThanOrEqualTo(String value) {
			addCriterion("c_couponExpiryDate <=", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateLike(String value) {
			addCriterion("c_couponExpiryDate like", value, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateNotLike(String value) {
			addCriterion("c_couponExpiryDate not like", value,
					"couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateIn(List<String> values) {
			addCriterion("c_couponExpiryDate in", values, "couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateNotIn(List<String> values) {
			addCriterion("c_couponExpiryDate not in", values,
					"couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateBetween(String value1, String value2) {
			addCriterion("c_couponExpiryDate between", value1, value2,
					"couponExpiryDate");
			return (Criteria) this;
		}

		public Criteria andCouponExpiryDateNotBetween(String value1,
				String value2) {
			addCriterion("c_couponExpiryDate not between", value1, value2,
					"couponExpiryDate");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_campain
	 * @mbggenerated  Thu Jun 22 09:41:38 CST 2017
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table app_fd_ec_campain
     *
     * @mbggenerated do_not_delete_during_merge Thu Jun 08 14:13:02 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}