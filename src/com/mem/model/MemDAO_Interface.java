package com.mem.model;

import java.util.List;
import java.util.Set;

import com.com.model.ComVO;





public interface MemDAO_Interface {
	
	 public void insert(MemVO memVO);
     public void update(MemVO memVO);
     public void delete(String mem_no);
     public MemVO findByPrimaryKey(String mem_no);
     public List<MemVO> getAll();
     public Set<MemVO> getMemsByReport(Integer report);
     public Set<MemVO> getMemsByStatus(String status);
     public List<MemVO> loginid();
     public List<MemVO> loginpwd();
     public MemVO findById(String id);
     public void updatePwd(MemVO memVO);
     public void updateStatus(MemVO memVO);
     public MemVO oldPwd(String mem_no);
     public void updatePic(MemVO memVO);
     public void updateReport(MemVO memVO);
     public void updateStatusForReport(MemVO memVO);
}
