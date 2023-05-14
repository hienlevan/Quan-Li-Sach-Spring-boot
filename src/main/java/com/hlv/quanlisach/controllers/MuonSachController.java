package com.hlv.quanlisach.controllers;

import com.hlv.quanlisach.dtos.muonsach.MuonSachDto;
import com.hlv.quanlisach.entities.MuonSach;
import com.hlv.quanlisach.services.muonsach.MuonSachService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/muon-sach")
public class MuonSachController {

    private final MuonSachService muonSachService;

    public MuonSachController(MuonSachService muonSachService) {
        this.muonSachService = muonSachService;
    }

    @GetMapping()
    public ResponseEntity<List<MuonSach>> getAllSachs(){
        return new ResponseEntity<>(muonSachService.getAllMuonSachs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuonSach> getSachById(@PathVariable String id){
        return  new ResponseEntity<>(muonSachService.getMuonSach(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Mượn sách")
    @PostMapping
    public ResponseEntity<MuonSach> create(@Valid @RequestBody MuonSachDto dto, Principal principal){
        return new ResponseEntity<>(muonSachService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Mượn sách")
    @PutMapping("/{id}")
    public ResponseEntity<MuonSach> update(@PathVariable String id, @Valid @RequestBody MuonSachDto dto,
                                                Principal principal){
        return  new ResponseEntity<>(muonSachService.update(id, dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Mượn sách")
    @DeleteMapping("/{id}")
    public ResponseEntity<MuonSach> delete(@PathVariable String id, Principal principal){
        return  new ResponseEntity<>(muonSachService.delete(id, principal), HttpStatus.OK);
    }
}
