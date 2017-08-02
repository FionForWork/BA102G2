package com.protracking_list.model;

import java.util.List;

public interface Protracking_listDAO_Interface {

    void add(Protracking_listVO protracking_listVO);

    void delete(String protra_no);

    void update(Protracking_listVO protracking_listVO);

    Protracking_listVO getOneByPK(String protra_no);

    List<Protracking_listVO> getAllByMem(String mem_no);

    List<Protracking_listVO> getAll();

    int getRowCount(String mem_no);

    List<Protracking_listVO> getSome(String mem_no, int nowPage, int count);
}
