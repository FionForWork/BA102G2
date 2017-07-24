package com.res_detail.model;

import java.util.List;

public interface Res_DetailDAO_Interface {
	void insert(Res_DetailVO res_detailVO);
	void updateStatus(Res_DetailVO res_detailVO);
	void delete(String rd_no);
	Res_DetailVO findByPK(String rd_no);
	List<Res_DetailVO> getAll();
}
