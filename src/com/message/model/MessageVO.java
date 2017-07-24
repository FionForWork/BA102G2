package com.message.model;

public class MessageVO implements java.io.Serializable {
	private String msg_no;
	private String mem_no;
	private String com_no;
	private String content;

	public MessageVO() {
	}

	public String getMsg_no() {
		return msg_no;
	}

	public void setMsg_no(String msg_no) {
		this.msg_no = msg_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
