package com.article.model;

import java.util.*;



public interface ArticleDAO_interfacce {
	  public void insert(ArticleVO artVO);
      public void update(ArticleVO artVO);
      public void delete(Integer article_no);
      public ArticleVO findByPrimaryKey(Integer article_no);
      public List<ArticleVO> getAll();
}
