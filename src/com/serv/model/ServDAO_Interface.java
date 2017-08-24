package com.serv.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mem.model.MemVO;


public interface ServDAO_Interface {

	
	
	public void insert(ServVO servVO);
    public void update(ServVO servVO);
    public void updateScore(ServVO servVO);
    public void delete(String serv_no);
    public ServVO findByPrimaryKey(String serv_no);
    public List<ServVO> getAll();
    public List<ServVO> getAll(Map<String, String[]> map);
    public List<String> findByStype_no(String stype_no);
    public List<ServVO> getCom(String com_no);
    public Set<ServVO> getServByStype(String stype_no);
    public Set<ServVO> getServByCom(String com_no);
    public List<ServVO> findBysh(String sh);
    public void downStatus(ServVO servVO);
    public void upStatus(ServVO servVO);
    public List<ServVO> getAllAvg();
    public void updateStatus(ServVO servVO);
    public List<String> getComStype(String com_no);

}

