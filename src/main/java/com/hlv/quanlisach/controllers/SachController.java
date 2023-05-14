package com.hlv.quanlisach.controllers;

import com.hlv.quanlisach.dtos.sach.SachDto;
import com.hlv.quanlisach.entities.Sach;
import com.hlv.quanlisach.services.sach.SachService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/sach")
public class SachController {

    private final SachService sachService;

    public SachController(SachService sachService) {
        this.sachService = sachService;
    }


    @GetMapping()
    public ResponseEntity<List<Sach>> getAllSachs(){
        return new ResponseEntity<>(sachService.getAllSachs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sach> getSachById(@PathVariable String id){
        return  new ResponseEntity<>(sachService.getSach(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Sách")
    @PostMapping
    public ResponseEntity<Sach> create(@Valid @RequestBody SachDto dto, Principal principal){
        return new ResponseEntity<>(sachService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Sách")
    @PutMapping("/{id}")
    public ResponseEntity<Sach> update(@PathVariable String id, @Valid @RequestBody SachDto dto,
                                         Principal principal){
        return  new ResponseEntity<>(sachService.update(id, dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Sách")
    @DeleteMapping("/{id}")
    public ResponseEntity<Sach> delete(@PathVariable String id, Principal principal){
        return  new ResponseEntity<>(sachService.delete(id, principal), HttpStatus.OK);
    }
}
