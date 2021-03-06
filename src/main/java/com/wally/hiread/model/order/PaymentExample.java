package com.wally.hiread.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public PaymentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
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

		public Criteria andAmountIsNull() {
			addCriterion("c_amount is null");
			return (Criteria) this;
		}

		public Criteria andAmountIsNotNull() {
			addCriterion("c_amount is not null");
			return (Criteria) this;
		}

		public Criteria andAmountEqualTo(String value) {
			addCriterion("c_amount =", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotEqualTo(String value) {
			addCriterion("c_amount <>", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThan(String value) {
			addCriterion("c_amount >", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThanOrEqualTo(String value) {
			addCriterion("c_amount >=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThan(String value) {
			addCriterion("c_amount <", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThanOrEqualTo(String value) {
			addCriterion("c_amount <=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLike(String value) {
			addCriterion("c_amount like", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotLike(String value) {
			addCriterion("c_amount not like", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountIn(List<String> values) {
			addCriterion("c_amount in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotIn(List<String> values) {
			addCriterion("c_amount not in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountBetween(String value1, String value2) {
			addCriterion("c_amount between", value1, value2, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotBetween(String value1, String value2) {
			addCriterion("c_amount not between", value1, value2, "amount");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("c_user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("c_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(String value) {
			addCriterion("c_user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(String value) {
			addCriterion("c_user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(String value) {
			addCriterion("c_user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("c_user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(String value) {
			addCriterion("c_user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(String value) {
			addCriterion("c_user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLike(String value) {
			addCriterion("c_user_id like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotLike(String value) {
			addCriterion("c_user_id not like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<String> values) {
			addCriterion("c_user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<String> values) {
			addCriterion("c_user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(String value1, String value2) {
			addCriterion("c_user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(String value1, String value2) {
			addCriterion("c_user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andTransactionNoIsNull() {
			addCriterion("c_transactionNo is null");
			return (Criteria) this;
		}

		public Criteria andTransactionNoIsNotNull() {
			addCriterion("c_transactionNo is not null");
			return (Criteria) this;
		}

		public Criteria andTransactionNoEqualTo(String value) {
			addCriterion("c_transactionNo =", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoNotEqualTo(String value) {
			addCriterion("c_transactionNo <>", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoGreaterThan(String value) {
			addCriterion("c_transactionNo >", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoGreaterThanOrEqualTo(String value) {
			addCriterion("c_transactionNo >=", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoLessThan(String value) {
			addCriterion("c_transactionNo <", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoLessThanOrEqualTo(String value) {
			addCriterion("c_transactionNo <=", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoLike(String value) {
			addCriterion("c_transactionNo like", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoNotLike(String value) {
			addCriterion("c_transactionNo not like", value, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoIn(List<String> values) {
			addCriterion("c_transactionNo in", values, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoNotIn(List<String> values) {
			addCriterion("c_transactionNo not in", values, "transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoBetween(String value1, String value2) {
			addCriterion("c_transactionNo between", value1, value2,
					"transactionNo");
			return (Criteria) this;
		}

		public Criteria andTransactionNoNotBetween(String value1, String value2) {
			addCriterion("c_transactionNo not between", value1, value2,
					"transactionNo");
			return (Criteria) this;
		}

		public Criteria andChannelIsNull() {
			addCriterion("c_channel is null");
			return (Criteria) this;
		}

		public Criteria andChannelIsNotNull() {
			addCriterion("c_channel is not null");
			return (Criteria) this;
		}

		public Criteria andChannelEqualTo(String value) {
			addCriterion("c_channel =", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotEqualTo(String value) {
			addCriterion("c_channel <>", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThan(String value) {
			addCriterion("c_channel >", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThanOrEqualTo(String value) {
			addCriterion("c_channel >=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThan(String value) {
			addCriterion("c_channel <", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThanOrEqualTo(String value) {
			addCriterion("c_channel <=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLike(String value) {
			addCriterion("c_channel like", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotLike(String value) {
			addCriterion("c_channel not like", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelIn(List<String> values) {
			addCriterion("c_channel in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotIn(List<String> values) {
			addCriterion("c_channel not in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelBetween(String value1, String value2) {
			addCriterion("c_channel between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotBetween(String value1, String value2) {
			addCriterion("c_channel not between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdIsNull() {
			addCriterion("c_order_id is null");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdIsNotNull() {
			addCriterion("c_order_id is not null");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdEqualTo(String value) {
			addCriterion("c_order_id =", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdNotEqualTo(String value) {
			addCriterion("c_order_id <>", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdGreaterThan(String value) {
			addCriterion("c_order_id >", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdGreaterThanOrEqualTo(String value) {
			addCriterion("c_order_id >=", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdLessThan(String value) {
			addCriterion("c_order_id <", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdLessThanOrEqualTo(String value) {
			addCriterion("c_order_id <=", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdLike(String value) {
			addCriterion("c_order_id like", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdNotLike(String value) {
			addCriterion("c_order_id not like", value, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdIn(List<String> values) {
			addCriterion("c_order_id in", values, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdNotIn(List<String> values) {
			addCriterion("c_order_id not in", values, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdBetween(String value1, String value2) {
			addCriterion("c_order_id between", value1, value2, "prodOrderId");
			return (Criteria) this;
		}

		public Criteria andProdOrderIdNotBetween(String value1, String value2) {
			addCriterion("c_order_id not between", value1, value2,
					"prodOrderId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_payment
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
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
     * This class corresponds to the database table app_fd_ec_payment
     *
     * @mbggenerated do_not_delete_during_merge Thu Jun 08 14:02:28 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}