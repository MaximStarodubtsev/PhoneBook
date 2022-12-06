package com.example.spring.Controllers;

import com.example.spring.DTO.PCDTO;
import com.example.spring.Model.PC;
import com.example.spring.Service.PCService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pcs")
@RequiredArgsConstructor
@ResponseBody
public class PCsController {

    private final PCService pcService;

    @GetMapping("/{id}")
    public Object gettingPage(@PathVariable("id") int id){
        try {
            Pageable page = PageRequest.of(id, 2);
            List<PCDTO> list = pcService.findPage(page);
            return (list!=null)&&(!list.isEmpty())?list:"No data";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @GetMapping("/add/{model}/{HDD}/{RAM}/{invNum}")
    public String addingPC(@PathVariable("model") String model,
                         @PathVariable("HDD") String HDD,
                         @PathVariable("RAM") String RAM,
                         @PathVariable("invNum") String invNum){
        try {
            pcService.saveAndFlush(PC.builder()
                    .model(model)
                    .hdd(HDD)
                    .ram(RAM)
                    .inventorynumber(invNum)
                    .build());
            return "";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @DeleteMapping("/delete/{invNum}")
    public String deletingPC(@PathVariable("invNum") String invNum){
        try {
            pcService.deleteByInvNum(invNum);
            return "";
        } catch (Exception e){
            return "Invalid data";
        }
    }
}

