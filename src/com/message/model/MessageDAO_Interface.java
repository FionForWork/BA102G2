package com.message.model;

import java.util.List;

public interface MessageDAO_Interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(String msg_no);
	public MessageVO findByPrimaryKey(String msg_no);
	public List<String> getMessageByMem_no(String mem_no);
	public List<MessageVO> getAll();
}
