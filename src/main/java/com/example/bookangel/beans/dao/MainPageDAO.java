package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MainPageVO;
import com.example.bookangel.mappers.MainPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainPageDAO {
    private final MainPageMapper mainPageMapper;

    public List<MainPageVO> getOkList(Criteria criteria){
        return mainPageMapper.getOkList(criteria);
    }

    public int getTotal(Criteria criteria){ return mainPageMapper.getTotal(criteria); }
}
