package com.eparam.model;

import java.util.List;

public class EparamService {
	private EparamDAO_Interface dao;

	public EparamService() {
		dao = new EparamDAO();
	}

	public List<EparamVO> getAll() {
		return dao.getAll();
	}

	public EparamVO getOneEparam(String eparam_no) {
		return dao.findByPrimaryKey(eparam_no);
	}

	public void deleteEparam(String eparam_no) {
		dao.delete(eparam_no);
	}
}
