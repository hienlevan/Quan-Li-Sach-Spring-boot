package com.hlv.quanlisach.controllers;


import com.hlv.quanlisach.dtos.nguoimuonsach.NguoiMuonSachDto;
import com.hlv.quanlisach.entities.NguoiMuonSach;
import com.hlv.quanlisach.services.nguoimuonsach.NguoiMuonSachService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/nguoi-muon-sach")
public class NguoiMuonSachController {

    private final NguoiMuonSachService nguoiMuonSachService;

    public NguoiMuonSachController(NguoiMuonSachService nguoiMuonSachService) {
        this.nguoiMuonSachService = nguoiMuonSachService;
    }


    @GetMapping()
    public ResponseEntity<List<NguoiMuonSach>> getAllSachs(){
        return new ResponseEntity<>(nguoiMuonSachService.getAllNguoiMuonSachs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiMuonSach> getSachById(@PathVariable String id){
        return  new ResponseEntity<>(nguoiMuonSachService.getNguoiMuonSach(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Người mượn sách")
    @PostMapping
    public ResponseEntity<NguoiMuonSach> create(@Valid @RequestBody NguoiMuonSachDto dto, Principal principal){
        return new ResponseEntity<>(nguoiMuonSachService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Người mượn sách")
    @PutMapping("/{id}")
    public ResponseEntity<NguoiMuonSach> update(@PathVariable String id, @Valid @RequestBody NguoiMuonSachDto dto,
                                       Principal principal){
        return  new ResponseEntity<>(nguoiMuonSachService.update(id, dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Người mượn sách")
    @DeleteMapping("/{id}")
    public ResponseEntity<NguoiMuonSach> delete(@PathVariable String id, Principal principal){
        return  new ResponseEntity<>(nguoiMuonSachService.delete(id, principal), HttpStatus.OK);
    }
}
