package com.forum_comment.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Forum_Comment_Service {
	private Forum_Comment_interface dao;

	public Forum_Comment_Service() {
		dao = new Forum_CommentJNDIDAO();
	}

	public Forum_CommentVO addFC(Integer art_no, Integer speaker_no, String cont, java.sql.Timestamp t) {

		Forum_CommentVO forum_CommentVO = new Forum_CommentVO();
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		forum_CommentVO.setArt_no(art_no);
		forum_CommentVO.setSpeaker_no(speaker_no);
		forum_CommentVO.setCont(cont);
		forum_CommentVO.setFmc_date(t1);

		dao.insert(forum_CommentVO);

		return forum_CommentVO;

	}

	public Forum_CommentVO updateFC(Integer fmc_no, Integer art_no, Integer speaker_no, String cont,
			java.sql.Timestamp fmc_date) {

		Forum_CommentVO forum_CommentVO = new Forum_CommentVO();
		Timestamp t = new Timestamp(System.currentTimeMillis());
		forum_CommentVO.setArt_no(art_no);
		forum_CommentVO.setSpeaker_no(speaker_no);
		forum_CommentVO.setCont(cont);
		forum_CommentVO.setFmc_date(t);
		forum_CommentVO.setFmc_no(fmc_no);

		dao.update(forum_CommentVO);

		return forum_CommentVO;

	}

	public List<Forum_CommentVO> getAll() {
		return dao.getAll();
	}

	public Forum_CommentVO getOneForum_Comment(Integer fmc_no) {
		return dao.findByPrimaryKey(fmc_no);
	}

	public void deleteForum_Comment(Integer fmc_no) {
		dao.delete(fmc_no);
	}

	public Forum_CommentVO getOneAll(Integer art_no) {

		return dao.getOneAll(art_no);
	}

}
