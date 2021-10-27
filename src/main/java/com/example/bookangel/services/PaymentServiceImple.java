package com.example.bookangel.services;

import com.example.bookangel.beans.dao.PaymentDAO;
import com.example.bookangel.beans.vo.PaymentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImple implements PaymentService{

    private final PaymentDAO paymentDAO;

    @Override
    public boolean subscribe(PaymentVO paymentVO){
        return paymentDAO.subscribe(paymentVO);
    }

    @Override
    public boolean paymentExist(PaymentVO paymentVO){
        return paymentDAO.paymentExist(paymentVO);
    }

    @Override
    public boolean resubscribe(PaymentVO paymentVO){ return paymentDAO.resubscribe(paymentVO); }

    @Override
    public boolean subscribeExist(int memberNum){ return paymentDAO.subscribeExist(memberNum); }
}
