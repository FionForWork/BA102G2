package com.fun.model;

import java.util.List;



public interface FunDAO_Interface {

	public void insert(FunVO funVO);


    public void delete(String fun_no);
    public FunVO findByPrimaryKey(String fun_no);
    public List<FunVO> getAll();
}
