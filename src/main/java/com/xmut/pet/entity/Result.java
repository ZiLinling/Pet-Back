package com.xmut.pet.entity;




import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求返回结果对象
 * @author bill.lin
 *
 */
public class Result<T> implements Serializable{

	private String statusCode = ResultType.SUCCESS.getCode();

	private String message = ResultType.SUCCESS.getName();

	private T data = null;

	private Object etc;

	public void put(String key, Object value) {
		if(etc == null) {
			etc = new HashMap<String, Object>();
		}
		((Map<String, Object>) etc).put(key, value);
	}
	
	/**
	 * 获取状态编码
	 * @return
	 */
	public String getStatusCode() {
		return statusCode;
	}
	
	/**
	 * 设置状态编码
	 * @param statusCode
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * 获取提示信息
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置提示信息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取返回结果
	 * @return
	 */
	public T getData() {
		return data;
	}

	public Object getEtc() {
		return etc;
	}

	/**
	 * 设置返回结果
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}


	public void addError() {
		this.addError("");
	}

	public void addError(String message) {
		this.message = message;
		this.statusCode = ResultType.INTERNAL_SERVER_ERROR.getCode();
		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.INTERNAL_SERVER_ERROR.getName();
		}
	}

	public void success() {
		this.success("");
	}

	public void success(String message) {
		this.message = message;
		this.statusCode = ResultType.SUCCESS.getCode();
		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.SUCCESS.getName();
		}
	}

	public void fail() {
		this.fail("");
	}

	public void fail(String message) {
		this.message = message;
		this.statusCode = ResultType.FAIL.getCode();
		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.FAIL.getName();
		}
	}

	public void unauthorized() {
		this.unauthorized("");
	}

	public void unauthorized(String message) {
		this.message = message;
		this.statusCode = ResultType.UNAUTHORIZED.getCode();

		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.UNAUTHORIZED.getName();
		}
	}

	public void notFound() {
		this.notFound("");
	}

	public void notFound(String message) {
		this.message = message;
		this.statusCode = ResultType.NOT_FOUND.getCode();
		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.NOT_FOUND.getName();
		}
	}

	public void againLogin() {
		this.againLogin("");
	}

	public void againLogin(String message) {
		this.message = message;
		this.statusCode = ResultType.AGAIN_LOGIN.getCode();
		if(this.message == null || "".equals(this.message)){
			this.message = ResultType.AGAIN_LOGIN.getName();
		}
	}
}
