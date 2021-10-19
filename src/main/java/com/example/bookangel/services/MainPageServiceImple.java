package com.example.bookangel.services;

import com.example.bookangel.beans.dao.MainPageDAO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MainPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageServiceImple implements MainPageService {
    private final MainPageDAO mainPageDAO;

    @Override
    public List<MainPageVO> getOkList(Criteria criteria) {
        return mainPageDAO.getOkList(criteria); }

    @Override
    public int getOkTotal(Criteria criteria) { return mainPageDAO.getOkTotal(criteria); }
}
