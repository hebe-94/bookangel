package com.example.bookangel.services;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MainPageVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainPageService {
    public List<MainPageVO> getOkList(Criteria criteria);
    public int getOkTotal(Criteria criteria);
}
