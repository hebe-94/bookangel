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
    public void subscribe(PaymentVO paymentVO){
        paymentDAO.subscribe(paymentVO);
    }

}
