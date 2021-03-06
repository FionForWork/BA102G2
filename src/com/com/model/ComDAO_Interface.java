package com.com.model;

import java.util.*;

import com.mem.model.MemVO;





public interface ComDAO_Interface {
	public void insert(ComVO comVO);
    public void update(ComVO comVO);
    public void delete(String com_no);
    public ComVO findByPrimaryKey(String com_no);
    public List<ComVO> getAll();
    public List<ComVO> getAll(Map<String, String[]> map);
    public List<ComVO> loginid();
    public List<ComVO> loginpwd();
    public ComVO findById(String id);
    public void updatePwd(ComVO comVO);
    public ComVO oldPwd(String com_no);
    public void confirmCom(ComVO comVO);
    public void updatePic(ComVO comVO);
    public void updateStatus(ComVO comVO);
    public List<ComVO> findBylocation(String lon1,String lon2,String lat1,String lat2);
}
