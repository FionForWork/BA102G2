package com.protra.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;
import com.sun.org.apache.regexp.internal.recompile;

public class ProtraService {
    private ProtraDAO_Interface dao;

    public ProtraService() {
        dao = new ProtraDAO();
    }

    public void addProtracking_list(String pro_no, String mem_no) {
        ProtraVO protracking_listVO = new ProtraVO();
        protracking_listVO.setPro_no(pro_no);
        protracking_listVO.setMem_no(mem_no);
        dao.add(protracking_listVO);
    }

    public void addProtracking_list(ProtraVO protracking_listVO) {
        dao.add(protracking_listVO);
    }

    public void deleteProductTracking(String pro_no) {
        dao.delete(pro_no);
    }

    public ProtraVO getOneProtracking_list(String protra_no) {
        return dao.getOneByPK(protra_no);
    }

    public List<ProtraVO> getAll() {
        return dao.getAll();
    }

    public List<ProtraVO> getAllByMem(String mem_no) {
        return dao.getAllByMem(mem_no);
    }

    public int getRowCount(String mem_no) {
        return dao.getRowCount(mem_no);
    }

    public List<String> getSome(String mem_no, int nowPage, int count) {
        List<String> list = new ArrayList<String>();
        List<ProtraVO> protracking_list = dao.getSome(mem_no, nowPage, count);
        for (int i = 0; i < protracking_list.size(); i++) {
            list.add(protracking_list.get(i).getPro_no());
        }
        return list;
    }
}
