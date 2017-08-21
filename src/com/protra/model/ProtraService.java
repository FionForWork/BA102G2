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

    public void insert(String pro_no, String mem_no) {
        ProtraVO protraVO = new ProtraVO();
        protraVO.setPro_no(pro_no);
        protraVO.setMem_no(mem_no);
        dao.insert(protraVO);
    }

    public void insert(ProtraVO protraVO) {
        dao.insert(protraVO);
    }

    public void deleteByComposite(String pro_no,String mem_no) {
        dao.deleteByComposite(pro_no,mem_no);
    }
    
    public void deleteByFK(String pro_no) {
        dao.deleteByFK(pro_no);
    }

    public ProtraVO getOneByPK(String protra_no) {
        return dao.getOneByPK(protra_no);
    }

    public List<ProtraVO> getAllByMem(String mem_no) {
        return dao.getAllByMem(mem_no);
    }

    public int getAllCount(String mem_no) {
        return dao.getAllCount(mem_no);
    }

    public List<String> getPage(int nowPage, int itemsCount,String mem_no) {
        List<String> list = new ArrayList<String>();
        List<ProtraVO> protraList = dao.getPage(nowPage, itemsCount,mem_no);
        for (int i = 0; i < protraList.size(); i++) {
            list.add(protraList.get(i).getPro_no());
        }
        return list;
    }
}
