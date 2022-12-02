package com.example.spring.Service;

import com.example.spring.DAO.PCRepository;
import com.example.spring.Model.PC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PCService {
    private final PCRepository pcRepository;

    public void saveAndFlush(PC pc){
        pcRepository.saveAndFlush(pc);
    }

    public Optional<PC> findByInvNum(String invNum){
        return pcRepository.findByInventorynumber(invNum);
    }
}

