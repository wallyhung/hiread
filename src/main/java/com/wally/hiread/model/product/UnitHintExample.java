package com.wally.hiread.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnitHintExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public UnitHintExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
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
            addCriterion("dateCreated not between", value1, value2, "datecreated");
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
            addCriterion("dateModified not between", value1, value2, "datemodified");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("c_instruction is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("c_instruction is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(String value) {
            addCriterion("c_instruction =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(String value) {
            addCriterion("c_instruction <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(String value) {
            addCriterion("c_instruction >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("c_instruction >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(String value) {
            addCriterion("c_instruction <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(String value) {
            addCriterion("c_instruction <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLike(String value) {
            addCriterion("c_instruction like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotLike(String value) {
            addCriterion("c_instruction not like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<String> values) {
            addCriterion("c_instruction in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<String> values) {
            addCriterion("c_instruction not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(String value1, String value2) {
            addCriterion("c_instruction between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(String value1, String value2) {
            addCriterion("c_instruction not between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("c_product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("c_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("c_product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("c_product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("c_product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("c_product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("c_product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("c_product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("c_product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("c_product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("c_product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("c_product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("c_product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("c_product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andTimeSecIsNull() {
            addCriterion("c_timeSec is null");
            return (Criteria) this;
        }

        public Criteria andTimeSecIsNotNull() {
            addCriterion("c_timeSec is not null");
            return (Criteria) this;
        }

        public Criteria andTimeSecEqualTo(String value) {
            addCriterion("c_timeSec =", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecNotEqualTo(String value) {
            addCriterion("c_timeSec <>", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecGreaterThan(String value) {
            addCriterion("c_timeSec >", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecGreaterThanOrEqualTo(String value) {
            addCriterion("c_timeSec >=", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecLessThan(String value) {
            addCriterion("c_timeSec <", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecLessThanOrEqualTo(String value) {
            addCriterion("c_timeSec <=", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecLike(String value) {
            addCriterion("c_timeSec like", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecNotLike(String value) {
            addCriterion("c_timeSec not like", value, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecIn(List<String> values) {
            addCriterion("c_timeSec in", values, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecNotIn(List<String> values) {
            addCriterion("c_timeSec not in", values, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecBetween(String value1, String value2) {
            addCriterion("c_timeSec between", value1, value2, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeSecNotBetween(String value1, String value2) {
            addCriterion("c_timeSec not between", value1, value2, "timeSec");
            return (Criteria) this;
        }

        public Criteria andTimeMinIsNull() {
            addCriterion("c_timeMin is null");
            return (Criteria) this;
        }

        public Criteria andTimeMinIsNotNull() {
            addCriterion("c_timeMin is not null");
            return (Criteria) this;
        }

        public Criteria andTimeMinEqualTo(String value) {
            addCriterion("c_timeMin =", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinNotEqualTo(String value) {
            addCriterion("c_timeMin <>", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinGreaterThan(String value) {
            addCriterion("c_timeMin >", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinGreaterThanOrEqualTo(String value) {
            addCriterion("c_timeMin >=", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinLessThan(String value) {
            addCriterion("c_timeMin <", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinLessThanOrEqualTo(String value) {
            addCriterion("c_timeMin <=", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinLike(String value) {
            addCriterion("c_timeMin like", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinNotLike(String value) {
            addCriterion("c_timeMin not like", value, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinIn(List<String> values) {
            addCriterion("c_timeMin in", values, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinNotIn(List<String> values) {
            addCriterion("c_timeMin not in", values, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinBetween(String value1, String value2) {
            addCriterion("c_timeMin between", value1, value2, "timeMin");
            return (Criteria) this;
        }

        public Criteria andTimeMinNotBetween(String value1, String value2) {
            addCriterion("c_timeMin not between", value1, value2, "timeMin");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNull() {
            addCriterion("c_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("c_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(String value) {
            addCriterion("c_unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(String value) {
            addCriterion("c_unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(String value) {
            addCriterion("c_unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("c_unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(String value) {
            addCriterion("c_unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(String value) {
            addCriterion("c_unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLike(String value) {
            addCriterion("c_unit_id like", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotLike(String value) {
            addCriterion("c_unit_id not like", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<String> values) {
            addCriterion("c_unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<String> values) {
            addCriterion("c_unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(String value1, String value2) {
            addCriterion("c_unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(String value1, String value2) {
            addCriterion("c_unit_id not between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndIsNull() {
            addCriterion("c_timeMinEnd is null");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndIsNotNull() {
            addCriterion("c_timeMinEnd is not null");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndEqualTo(String value) {
            addCriterion("c_timeMinEnd =", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndNotEqualTo(String value) {
            addCriterion("c_timeMinEnd <>", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndGreaterThan(String value) {
            addCriterion("c_timeMinEnd >", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndGreaterThanOrEqualTo(String value) {
            addCriterion("c_timeMinEnd >=", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndLessThan(String value) {
            addCriterion("c_timeMinEnd <", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndLessThanOrEqualTo(String value) {
            addCriterion("c_timeMinEnd <=", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndLike(String value) {
            addCriterion("c_timeMinEnd like", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndNotLike(String value) {
            addCriterion("c_timeMinEnd not like", value, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndIn(List<String> values) {
            addCriterion("c_timeMinEnd in", values, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndNotIn(List<String> values) {
            addCriterion("c_timeMinEnd not in", values, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndBetween(String value1, String value2) {
            addCriterion("c_timeMinEnd between", value1, value2, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeMinEndNotBetween(String value1, String value2) {
            addCriterion("c_timeMinEnd not between", value1, value2, "timeMinEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndIsNull() {
            addCriterion("c_timeSecEnd is null");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndIsNotNull() {
            addCriterion("c_timeSecEnd is not null");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndEqualTo(String value) {
            addCriterion("c_timeSecEnd =", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndNotEqualTo(String value) {
            addCriterion("c_timeSecEnd <>", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndGreaterThan(String value) {
            addCriterion("c_timeSecEnd >", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndGreaterThanOrEqualTo(String value) {
            addCriterion("c_timeSecEnd >=", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndLessThan(String value) {
            addCriterion("c_timeSecEnd <", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndLessThanOrEqualTo(String value) {
            addCriterion("c_timeSecEnd <=", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndLike(String value) {
            addCriterion("c_timeSecEnd like", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndNotLike(String value) {
            addCriterion("c_timeSecEnd not like", value, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndIn(List<String> values) {
            addCriterion("c_timeSecEnd in", values, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndNotIn(List<String> values) {
            addCriterion("c_timeSecEnd not in", values, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndBetween(String value1, String value2) {
            addCriterion("c_timeSecEnd between", value1, value2, "timeSecEnd");
            return (Criteria) this;
        }

        public Criteria andTimeSecEndNotBetween(String value1, String value2) {
            addCriterion("c_timeSecEnd not between", value1, value2, "timeSecEnd");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated do_not_delete_during_merge Mon May 22 16:22:19 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table app_fd_ec_unit_hint
     *
     * @mbggenerated Mon May 22 16:22:19 CST 2017
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}