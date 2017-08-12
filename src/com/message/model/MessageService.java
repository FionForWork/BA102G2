package com.message.model;

import java.util.List;

import com.advertising.model.AdvertisingVO;

public class MessageService {
	private MessageDAO_Interface dao;

	public MessageService() {
		dao = new MessageDAO();
	}
	
	public MessageVO addMessage(String mem_no,String com_no, String content) {

		MessageVO messageVO = new MessageVO();
		messageVO.setMem_no(mem_no);
		messageVO.setCom_no(com_no);
		messageVO.setContent(content);
		dao.insert(messageVO);

		return messageVO;
	}
	

	public List<MessageVO> getAll() {
		return dao.getAll();
	}

	public MessageVO getOneMessage(String msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}
	
	public List<String> getMessageByMem_no(String mem_no) {
		return dao.getMessageByMem_no(mem_no);
	}

	public void deleteMessage(String msg_no) {
		dao.delete(msg_no);
	}

}
