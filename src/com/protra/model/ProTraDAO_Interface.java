package com.protra.model;

import java.sql.Connection;
import java.util.List;

public interface ProTraDAO_Interface {

    void insert(ProtraVO protraVO);

    void deleteByComposite(String pro_no,String mem_no);

    void deleteByFK(String pro_no);

    void deleteByFK(String pro_no,Connection connection);

    void update(ProtraVO protraVO);

    ProtraVO getOneByPK(String protra_no);

    List<ProtraVO> getAllByMem(String mem_no);

    int getAllCount(String mem_no);

    List<ProtraVO> getPage(int start, int itemsCount,String mem_no);
}
