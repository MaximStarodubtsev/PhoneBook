package com.example.spring.Controllers;

import com.example.spring.DTO.PCDTO;
import com.example.spring.Model.PC;
import com.example.spring.Service.PCService;
import jakarta.validation.constraints.*;
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

    private final static String errorMessage = "Invalid data or system error";
    private final PCService pcService;

    @GetMapping("/{id}")
    public Object gettingPage(@PathVariable("id") @Min(0) @Max(Integer.MAX_VALUE) int id){
        try {
            Pageable page = PageRequest.of(id, 2);
            List<PCDTO> list = pcService.findPage(page);
            return (list!=null)&&(!list.isEmpty())?list:"No data";
        } catch (Exception e){
            return errorMessage;
        }
    }

    @GetMapping("/add/{model}/{HDD}/{RAM}/{invNum}")
    public String addingPC(@PathVariable("model") @Size(max=136) String model,
                         @PathVariable("HDD") @Size(max=136) String HDD,
                         @PathVariable("RAM") @Size(max=136) String RAM,
                         @PathVariable("invNum") @Size(max=136) @NotNull @NotEmpty String invNum){
        try {
            pcService.saveAndFlush(PC.builder()
                    .model(model)
                    .hdd(HDD)
                    .ram(RAM)
                    .inventorynumber(invNum)
                    .build());
            return "";
        } catch (Exception e){
            return errorMessage;
        }
    }

    @DeleteMapping("/delete/{invNum}")
    public String deletingPC(@PathVariable("invNum") @Size(max=136) @NotNull @NotEmpty String invNum){
        try {
            pcService.deleteByInvNum(invNum);
            return "";
        } catch (Exception e){
            return errorMessage;
        }
    }
}

