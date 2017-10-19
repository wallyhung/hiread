package com.wally.hiread.model.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestingExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public TestingExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
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

		public Criteria andQuestionIsNull() {
			addCriterion("c_question is null");
			return (Criteria) this;
		}

		public Criteria andQuestionIsNotNull() {
			addCriterion("c_question is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionEqualTo(String value) {
			addCriterion("c_question =", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotEqualTo(String value) {
			addCriterion("c_question <>", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionGreaterThan(String value) {
			addCriterion("c_question >", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionGreaterThanOrEqualTo(String value) {
			addCriterion("c_question >=", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLessThan(String value) {
			addCriterion("c_question <", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLessThanOrEqualTo(String value) {
			addCriterion("c_question <=", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionLike(String value) {
			addCriterion("c_question like", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotLike(String value) {
			addCriterion("c_question not like", value, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionIn(List<String> values) {
			addCriterion("c_question in", values, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotIn(List<String> values) {
			addCriterion("c_question not in", values, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionBetween(String value1, String value2) {
			addCriterion("c_question between", value1, value2, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionNotBetween(String value1, String value2) {
			addCriterion("c_question not between", value1, value2, "question");
			return (Criteria) this;
		}

		public Criteria andQuestionImgIsNull() {
			addCriterion("c_questionImg is null");
			return (Criteria) this;
		}

		public Criteria andQuestionImgIsNotNull() {
			addCriterion("c_questionImg is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionImgEqualTo(String value) {
			addCriterion("c_questionImg =", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgNotEqualTo(String value) {
			addCriterion("c_questionImg <>", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgGreaterThan(String value) {
			addCriterion("c_questionImg >", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgGreaterThanOrEqualTo(String value) {
			addCriterion("c_questionImg >=", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgLessThan(String value) {
			addCriterion("c_questionImg <", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgLessThanOrEqualTo(String value) {
			addCriterion("c_questionImg <=", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgLike(String value) {
			addCriterion("c_questionImg like", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgNotLike(String value) {
			addCriterion("c_questionImg not like", value, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgIn(List<String> values) {
			addCriterion("c_questionImg in", values, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgNotIn(List<String> values) {
			addCriterion("c_questionImg not in", values, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgBetween(String value1, String value2) {
			addCriterion("c_questionImg between", value1, value2, "questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionImgNotBetween(String value1, String value2) {
			addCriterion("c_questionImg not between", value1, value2,
					"questionImg");
			return (Criteria) this;
		}

		public Criteria andQuestionFileIsNull() {
			addCriterion("c_questionFile is null");
			return (Criteria) this;
		}

		public Criteria andQuestionFileIsNotNull() {
			addCriterion("c_questionFile is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionFileEqualTo(String value) {
			addCriterion("c_questionFile =", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileNotEqualTo(String value) {
			addCriterion("c_questionFile <>", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileGreaterThan(String value) {
			addCriterion("c_questionFile >", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileGreaterThanOrEqualTo(String value) {
			addCriterion("c_questionFile >=", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileLessThan(String value) {
			addCriterion("c_questionFile <", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileLessThanOrEqualTo(String value) {
			addCriterion("c_questionFile <=", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileLike(String value) {
			addCriterion("c_questionFile like", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileNotLike(String value) {
			addCriterion("c_questionFile not like", value, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileIn(List<String> values) {
			addCriterion("c_questionFile in", values, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileNotIn(List<String> values) {
			addCriterion("c_questionFile not in", values, "questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileBetween(String value1, String value2) {
			addCriterion("c_questionFile between", value1, value2,
					"questionFile");
			return (Criteria) this;
		}

		public Criteria andQuestionFileNotBetween(String value1, String value2) {
			addCriterion("c_questionFile not between", value1, value2,
					"questionFile");
			return (Criteria) this;
		}

		public Criteria andPaperLevelIsNull() {
			addCriterion("c_paperLevel is null");
			return (Criteria) this;
		}

		public Criteria andPaperLevelIsNotNull() {
			addCriterion("c_paperLevel is not null");
			return (Criteria) this;
		}

		public Criteria andPaperLevelEqualTo(String value) {
			addCriterion("c_paperLevel =", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelNotEqualTo(String value) {
			addCriterion("c_paperLevel <>", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelGreaterThan(String value) {
			addCriterion("c_paperLevel >", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelGreaterThanOrEqualTo(String value) {
			addCriterion("c_paperLevel >=", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelLessThan(String value) {
			addCriterion("c_paperLevel <", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelLessThanOrEqualTo(String value) {
			addCriterion("c_paperLevel <=", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelLike(String value) {
			addCriterion("c_paperLevel like", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelNotLike(String value) {
			addCriterion("c_paperLevel not like", value, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelIn(List<String> values) {
			addCriterion("c_paperLevel in", values, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelNotIn(List<String> values) {
			addCriterion("c_paperLevel not in", values, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelBetween(String value1, String value2) {
			addCriterion("c_paperLevel between", value1, value2, "paperLevel");
			return (Criteria) this;
		}

		public Criteria andPaperLevelNotBetween(String value1, String value2) {
			addCriterion("c_paperLevel not between", value1, value2,
					"paperLevel");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoIsNull() {
			addCriterion("c_questionVideo is null");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoIsNotNull() {
			addCriterion("c_questionVideo is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoEqualTo(String value) {
			addCriterion("c_questionVideo =", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoNotEqualTo(String value) {
			addCriterion("c_questionVideo <>", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoGreaterThan(String value) {
			addCriterion("c_questionVideo >", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoGreaterThanOrEqualTo(String value) {
			addCriterion("c_questionVideo >=", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoLessThan(String value) {
			addCriterion("c_questionVideo <", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoLessThanOrEqualTo(String value) {
			addCriterion("c_questionVideo <=", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoLike(String value) {
			addCriterion("c_questionVideo like", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoNotLike(String value) {
			addCriterion("c_questionVideo not like", value, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoIn(List<String> values) {
			addCriterion("c_questionVideo in", values, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoNotIn(List<String> values) {
			addCriterion("c_questionVideo not in", values, "questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoBetween(String value1, String value2) {
			addCriterion("c_questionVideo between", value1, value2,
					"questionVideo");
			return (Criteria) this;
		}

		public Criteria andQuestionVideoNotBetween(String value1, String value2) {
			addCriterion("c_questionVideo not between", value1, value2,
					"questionVideo");
			return (Criteria) this;
		}

		public Criteria andChoiceCIsNull() {
			addCriterion("c_choiceC is null");
			return (Criteria) this;
		}

		public Criteria andChoiceCIsNotNull() {
			addCriterion("c_choiceC is not null");
			return (Criteria) this;
		}

		public Criteria andChoiceCEqualTo(String value) {
			addCriterion("c_choiceC =", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCNotEqualTo(String value) {
			addCriterion("c_choiceC <>", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCGreaterThan(String value) {
			addCriterion("c_choiceC >", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCGreaterThanOrEqualTo(String value) {
			addCriterion("c_choiceC >=", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCLessThan(String value) {
			addCriterion("c_choiceC <", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCLessThanOrEqualTo(String value) {
			addCriterion("c_choiceC <=", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCLike(String value) {
			addCriterion("c_choiceC like", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCNotLike(String value) {
			addCriterion("c_choiceC not like", value, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCIn(List<String> values) {
			addCriterion("c_choiceC in", values, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCNotIn(List<String> values) {
			addCriterion("c_choiceC not in", values, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCBetween(String value1, String value2) {
			addCriterion("c_choiceC between", value1, value2, "choiceC");
			return (Criteria) this;
		}

		public Criteria andChoiceCNotBetween(String value1, String value2) {
			addCriterion("c_choiceC not between", value1, value2, "choiceC");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerIsNull() {
			addCriterion("c_correctAnswer is null");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerIsNotNull() {
			addCriterion("c_correctAnswer is not null");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerEqualTo(String value) {
			addCriterion("c_correctAnswer =", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerNotEqualTo(String value) {
			addCriterion("c_correctAnswer <>", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerGreaterThan(String value) {
			addCriterion("c_correctAnswer >", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerGreaterThanOrEqualTo(String value) {
			addCriterion("c_correctAnswer >=", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerLessThan(String value) {
			addCriterion("c_correctAnswer <", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerLessThanOrEqualTo(String value) {
			addCriterion("c_correctAnswer <=", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerLike(String value) {
			addCriterion("c_correctAnswer like", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerNotLike(String value) {
			addCriterion("c_correctAnswer not like", value, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerIn(List<String> values) {
			addCriterion("c_correctAnswer in", values, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerNotIn(List<String> values) {
			addCriterion("c_correctAnswer not in", values, "correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerBetween(String value1, String value2) {
			addCriterion("c_correctAnswer between", value1, value2,
					"correctAnswer");
			return (Criteria) this;
		}

		public Criteria andCorrectAnswerNotBetween(String value1, String value2) {
			addCriterion("c_correctAnswer not between", value1, value2,
					"correctAnswer");
			return (Criteria) this;
		}

		public Criteria andChoiceDIsNull() {
			addCriterion("c_choiceD is null");
			return (Criteria) this;
		}

		public Criteria andChoiceDIsNotNull() {
			addCriterion("c_choiceD is not null");
			return (Criteria) this;
		}

		public Criteria andChoiceDEqualTo(String value) {
			addCriterion("c_choiceD =", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDNotEqualTo(String value) {
			addCriterion("c_choiceD <>", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDGreaterThan(String value) {
			addCriterion("c_choiceD >", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDGreaterThanOrEqualTo(String value) {
			addCriterion("c_choiceD >=", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDLessThan(String value) {
			addCriterion("c_choiceD <", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDLessThanOrEqualTo(String value) {
			addCriterion("c_choiceD <=", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDLike(String value) {
			addCriterion("c_choiceD like", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDNotLike(String value) {
			addCriterion("c_choiceD not like", value, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDIn(List<String> values) {
			addCriterion("c_choiceD in", values, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDNotIn(List<String> values) {
			addCriterion("c_choiceD not in", values, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDBetween(String value1, String value2) {
			addCriterion("c_choiceD between", value1, value2, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceDNotBetween(String value1, String value2) {
			addCriterion("c_choiceD not between", value1, value2, "choiceD");
			return (Criteria) this;
		}

		public Criteria andChoiceAIsNull() {
			addCriterion("c_choiceA is null");
			return (Criteria) this;
		}

		public Criteria andChoiceAIsNotNull() {
			addCriterion("c_choiceA is not null");
			return (Criteria) this;
		}

		public Criteria andChoiceAEqualTo(String value) {
			addCriterion("c_choiceA =", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceANotEqualTo(String value) {
			addCriterion("c_choiceA <>", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceAGreaterThan(String value) {
			addCriterion("c_choiceA >", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceAGreaterThanOrEqualTo(String value) {
			addCriterion("c_choiceA >=", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceALessThan(String value) {
			addCriterion("c_choiceA <", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceALessThanOrEqualTo(String value) {
			addCriterion("c_choiceA <=", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceALike(String value) {
			addCriterion("c_choiceA like", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceANotLike(String value) {
			addCriterion("c_choiceA not like", value, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceAIn(List<String> values) {
			addCriterion("c_choiceA in", values, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceANotIn(List<String> values) {
			addCriterion("c_choiceA not in", values, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceABetween(String value1, String value2) {
			addCriterion("c_choiceA between", value1, value2, "choiceA");
			return (Criteria) this;
		}

		public Criteria andChoiceANotBetween(String value1, String value2) {
			addCriterion("c_choiceA not between", value1, value2, "choiceA");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioIsNull() {
			addCriterion("c_questionAudio is null");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioIsNotNull() {
			addCriterion("c_questionAudio is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioEqualTo(String value) {
			addCriterion("c_questionAudio =", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioNotEqualTo(String value) {
			addCriterion("c_questionAudio <>", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioGreaterThan(String value) {
			addCriterion("c_questionAudio >", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioGreaterThanOrEqualTo(String value) {
			addCriterion("c_questionAudio >=", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioLessThan(String value) {
			addCriterion("c_questionAudio <", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioLessThanOrEqualTo(String value) {
			addCriterion("c_questionAudio <=", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioLike(String value) {
			addCriterion("c_questionAudio like", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioNotLike(String value) {
			addCriterion("c_questionAudio not like", value, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioIn(List<String> values) {
			addCriterion("c_questionAudio in", values, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioNotIn(List<String> values) {
			addCriterion("c_questionAudio not in", values, "questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioBetween(String value1, String value2) {
			addCriterion("c_questionAudio between", value1, value2,
					"questionAudio");
			return (Criteria) this;
		}

		public Criteria andQuestionAudioNotBetween(String value1, String value2) {
			addCriterion("c_questionAudio not between", value1, value2,
					"questionAudio");
			return (Criteria) this;
		}

		public Criteria andChoiceBIsNull() {
			addCriterion("c_choiceB is null");
			return (Criteria) this;
		}

		public Criteria andChoiceBIsNotNull() {
			addCriterion("c_choiceB is not null");
			return (Criteria) this;
		}

		public Criteria andChoiceBEqualTo(String value) {
			addCriterion("c_choiceB =", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBNotEqualTo(String value) {
			addCriterion("c_choiceB <>", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBGreaterThan(String value) {
			addCriterion("c_choiceB >", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBGreaterThanOrEqualTo(String value) {
			addCriterion("c_choiceB >=", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBLessThan(String value) {
			addCriterion("c_choiceB <", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBLessThanOrEqualTo(String value) {
			addCriterion("c_choiceB <=", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBLike(String value) {
			addCriterion("c_choiceB like", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBNotLike(String value) {
			addCriterion("c_choiceB not like", value, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBIn(List<String> values) {
			addCriterion("c_choiceB in", values, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBNotIn(List<String> values) {
			addCriterion("c_choiceB not in", values, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBBetween(String value1, String value2) {
			addCriterion("c_choiceB between", value1, value2, "choiceB");
			return (Criteria) this;
		}

		public Criteria andChoiceBNotBetween(String value1, String value2) {
			addCriterion("c_choiceB not between", value1, value2, "choiceB");
			return (Criteria) this;
		}

		public Criteria andSequenceIsNull() {
			addCriterion("c_sequence is null");
			return (Criteria) this;
		}

		public Criteria andSequenceIsNotNull() {
			addCriterion("c_sequence is not null");
			return (Criteria) this;
		}

		public Criteria andSequenceEqualTo(String value) {
			addCriterion("c_sequence =", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceNotEqualTo(String value) {
			addCriterion("c_sequence <>", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceGreaterThan(String value) {
			addCriterion("c_sequence >", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceGreaterThanOrEqualTo(String value) {
			addCriterion("c_sequence >=", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceLessThan(String value) {
			addCriterion("c_sequence <", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceLessThanOrEqualTo(String value) {
			addCriterion("c_sequence <=", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceLike(String value) {
			addCriterion("c_sequence like", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceNotLike(String value) {
			addCriterion("c_sequence not like", value, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceIn(List<String> values) {
			addCriterion("c_sequence in", values, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceNotIn(List<String> values) {
			addCriterion("c_sequence not in", values, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceBetween(String value1, String value2) {
			addCriterion("c_sequence between", value1, value2, "sequence");
			return (Criteria) this;
		}

		public Criteria andSequenceNotBetween(String value1, String value2) {
			addCriterion("c_sequence not between", value1, value2, "sequence");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table app_fd_ec_testing
	 * @mbggenerated  Thu Aug 31 14:03:15 CST 2017
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
     * This class corresponds to the database table app_fd_ec_testing
     *
     * @mbggenerated do_not_delete_during_merge Tue Aug 29 15:42:27 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}