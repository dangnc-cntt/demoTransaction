package com.example.doan.app.controllers;

import com.example.demotransaction.app.dtos.PointTransactionDTO;
import com.example.demotransaction.app.dtos.UserDTO;
import com.example.demotransaction.domain.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Controller {

    @Autowired
    private MainService mainService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UserDTO user) {
        mainService.save(user);
        return ResponseEntity.ok("ok!");
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody PointTransactionDTO dto)
    {
        return ResponseEntity.ok( mainService.addPoin(dto.getUserId(), dto.getAmount(), dto.getIsRollBack()));
    }
}
