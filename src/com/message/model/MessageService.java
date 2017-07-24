package com.message.model;

import java.util.List;

public class MessageService {
	private MessageDAO_Interface dao;

	public MessageService() {
		dao = new MessageDAO();
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}

	public MessageVO getOneDept(String msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}

	public void deleteDept(String msg_no) {
		dao.delete(msg_no);
	}

}
