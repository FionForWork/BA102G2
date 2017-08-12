package com.protra.model;

import java.util.List;

public interface ProtraDAO_Interface {

    void add(ProtraVO protracking_listVO);

    void delete(String protra_no);

    void update(ProtraVO protracking_listVO);

    ProtraVO getOneByPK(String protra_no);

    List<ProtraVO> getAllByMem(String mem_no);

    List<ProtraVO> getAll();

    int getRowCount(String mem_no);

    List<ProtraVO> getSome(String mem_no, int nowPage, int count);
}
