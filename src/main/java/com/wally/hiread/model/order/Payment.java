package com.wally.hiread.model.order;

import java.util.Date;

public class Payment {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.dateCreated
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private Date datecreated;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.dateModified
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private Date datemodified;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.c_amount
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String amount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.c_user_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.c_transactionNo
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String transactionNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.c_channel
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String channel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_fd_ec_payment.c_order_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	private String prodOrderId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.id
	 * @return  the value of app_fd_ec_payment.id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.id
	 * @param id  the value for app_fd_ec_payment.id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.dateCreated
	 * @return  the value of app_fd_ec_payment.dateCreated
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public Date getDatecreated() {
		return datecreated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.dateCreated
	 * @param datecreated  the value for app_fd_ec_payment.dateCreated
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.dateModified
	 * @return  the value of app_fd_ec_payment.dateModified
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public Date getDatemodified() {
		return datemodified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.dateModified
	 * @param datemodified  the value for app_fd_ec_payment.dateModified
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setDatemodified(Date datemodified) {
		this.datemodified = datemodified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.c_amount
	 * @return  the value of app_fd_ec_payment.c_amount
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.c_amount
	 * @param amount  the value for app_fd_ec_payment.c_amount
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.c_user_id
	 * @return  the value of app_fd_ec_payment.c_user_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.c_user_id
	 * @param userId  the value for app_fd_ec_payment.c_user_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.c_transactionNo
	 * @return  the value of app_fd_ec_payment.c_transactionNo
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getTransactionNo() {
		return transactionNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.c_transactionNo
	 * @param transactionNo  the value for app_fd_ec_payment.c_transactionNo
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.c_channel
	 * @return  the value of app_fd_ec_payment.c_channel
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.c_channel
	 * @param channel  the value for app_fd_ec_payment.c_channel
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_fd_ec_payment.c_order_id
	 * @return  the value of app_fd_ec_payment.c_order_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public String getProdOrderId() {
		return prodOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_fd_ec_payment.c_order_id
	 * @param prodOrderId  the value for app_fd_ec_payment.c_order_id
	 * @mbggenerated  Thu Jun 08 14:06:12 CST 2017
	 */
	public void setProdOrderId(String prodOrderId) {
		this.prodOrderId = prodOrderId;
	}
}