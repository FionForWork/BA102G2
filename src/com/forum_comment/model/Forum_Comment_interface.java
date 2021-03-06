package com.forum_comment.model;

import java.util.List;

import com.art_type.model.Art_TypeVO;

public interface Forum_Comment_interface {
	public void insert(Forum_CommentVO forum_CommentVO);
    public void update(Forum_CommentVO forum_CommentVO);
    public void delete(Integer fmc_no);
    public Forum_CommentVO findByPrimaryKey(Integer fmc_no);
    public List<Forum_CommentVO> getAll();
	public Forum_CommentVO getOneAll( Integer art_no);
	public Forum_CommentVO getArt_no_All(Integer art_no);
	public List<Forum_CommentVO> getOne_art_no(Integer art_no);
}
