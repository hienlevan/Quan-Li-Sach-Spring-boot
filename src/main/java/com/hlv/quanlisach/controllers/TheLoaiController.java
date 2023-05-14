package com.hlv.quanlisach.controllers;

import com.hlv.quanlisach.dtos.theloai.TheLoaiDto;
import com.hlv.quanlisach.entities.TheLoai;
import com.hlv.quanlisach.services.theloai.TheLoaiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping ("rest/the-loai")
public class TheLoaiController {

    private final TheLoaiService theLoaiService;

    public TheLoaiController(TheLoaiService theLoaiService) {
        this.theLoaiService = theLoaiService;
    }

    @GetMapping()
    public ResponseEntity<List<TheLoai>> getAllTheLoais(){
        return new ResponseEntity<>(theLoaiService.getAllTheLoais(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheLoai> getTheLoaiById(@PathVariable String id){
        return  new ResponseEntity<>(theLoaiService.getTheLoai(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Thể loại")
    @PostMapping
    public ResponseEntity<TheLoai> create(@Valid @RequestBody TheLoaiDto dto, Principal principal){
        return new ResponseEntity<>(theLoaiService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Thể loại")
    @PutMapping("/{id}")
    public ResponseEntity<TheLoai> update(@PathVariable String id, @Valid @RequestBody TheLoaiDto dto,
                                          Principal principal){
        return  new ResponseEntity<>(theLoaiService.update(id, dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Thể loại")
    @DeleteMapping("/{id}")
    public ResponseEntity<TheLoai> delete(@PathVariable String id, Principal principal){
        return  new ResponseEntity<>(theLoaiService.delete(id, principal), HttpStatus.OK);
    }

}
