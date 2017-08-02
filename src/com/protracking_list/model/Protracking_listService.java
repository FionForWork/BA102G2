package com.protracking_list.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;
import com.sun.org.apache.regexp.internal.recompile;

public class Protracking_listService {
    private Protracking_listDAO_Interface dao;

    public Protracking_listService() {
        dao = new Protracking_listDAO();
    }

    public void addProtracking_list(String pro_no, String mem_no) {
        Protracking_listVO protracking_listVO = new Protracking_listVO();
        protracking_listVO.setPro_no(pro_no);
        protracking_listVO.setMem_no(mem_no);
        dao.add(protracking_listVO);
    }

    public void addProtracking_list(Protracking_listVO protracking_listVO) {
        dao.add(protracking_listVO);
    }

    public void deleteProductTracking(String pro_no) {
        dao.delete(pro_no);
    }

    public Protracking_listVO getOneProtracking_list(String protra_no) {
        return dao.getOneByPK(protra_no);
    }

    public List<Protracking_listVO> getAll() {
        return dao.getAll();
    }

    public List<String> getAllByMem(String mem_no) {
        List<String> list = new ArrayList<String>();
        List<Protracking_listVO> protracking_list = dao.getAllByMem(mem_no);
        for (int i = 0; i < protracking_list.size(); i++) {
            list.add(protracking_list.get(i).getPro_no());
        }
        return list;
    }

    public int getRowCount(String mem_no) {
        return dao.getRowCount(mem_no);
    }

    public List<String> getSome(String mem_no, int nowPage, int count) {
        List<String> list = new ArrayList<String>();
        List<Protracking_listVO> protracking_list = dao.getSome(mem_no, nowPage, count);
        for (int i = 0; i < protracking_list.size(); i++) {
            list.add(protracking_list.get(i).getPro_no());
        }
        return list;
    }
}
