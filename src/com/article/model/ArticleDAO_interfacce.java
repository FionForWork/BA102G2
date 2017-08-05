package com.article.model;

import java.util.*;



public interface ArticleDAO_interfacce {
	  public void insert(ArticleVO artVO);
      public void update(ArticleVO artVO);
      public void delete(Integer art_no);
      public ArticleVO findByPrimaryKey(Integer art_no);
      public List<ArticleVO> getAll();
}
