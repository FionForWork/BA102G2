package com.works.model;

import java.util.List;

public interface WorksDAO_Interface {

	public String insertWorks(WorksVO works);
	public void deleteWorks(String works_no);
	public void updateWorks(WorksVO works);
	public WorksVO findWorksByPK(String works_no);
	public List<WorksVO> findWorksByComNo(String com_no);
	public List<WorksVO> findAll();
	public int countWorksInOneComNo(String com_no);

}
