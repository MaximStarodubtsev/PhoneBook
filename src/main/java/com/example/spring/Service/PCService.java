package com.example.spring.Service;

import com.example.spring.DAO.PCRepository;
import com.example.spring.DTO.MapperDTO;
import com.example.spring.DTO.PCDTO;
import com.example.spring.Model.PC;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PCService {
    private final PCRepository pcRepository;

    public List<PCDTO> findPage(Pageable page){
        Optional<List<PC>> pcs = pcRepository.findAllBy(page);
        return pcs.map(list->list.stream()
                .map(MapperDTO::pcDTOMap)
                .collect(Collectors.toList())).orElse(null);
    }

    public void deleteByInvNum(String invNum){
        pcRepository.deleteByInventorynumber(invNum);
    }

    public void saveAndFlush(PC pc){
        pcRepository.saveAndFlush(pc);
    }

    public Optional<PC> findByInvNum(String invNum){
        return pcRepository.findByInventorynumber(invNum);
    }
}

