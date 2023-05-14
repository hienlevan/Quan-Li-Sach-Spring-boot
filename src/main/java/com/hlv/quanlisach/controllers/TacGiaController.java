package com.hlv.quanlisach.controllers;

import com.hlv.quanlisach.dtos.tacgia.TacGiaDto;
import com.hlv.quanlisach.entities.TacGia;
import com.hlv.quanlisach.services.tacgia.TacGiaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/tac-gia")
public class TacGiaController {

    private final TacGiaService tacGiaService;

    public TacGiaController(TacGiaService tacGiaService) {
        this.tacGiaService = tacGiaService;
    }


    @GetMapping()
    public ResponseEntity<List<TacGia>> getAllTacGias(){
        return new ResponseEntity<>(tacGiaService.getAllTacGias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacGia> getTacGiaById(@PathVariable String id){
        return  new ResponseEntity<>(tacGiaService.getTacGia(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Tác giả")
    @PostMapping
    public ResponseEntity<TacGia> create(@Valid @RequestBody TacGiaDto dto, Principal principal){
        return new ResponseEntity<>(tacGiaService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Tác giả")
    @PutMapping("/{id}")
    public ResponseEntity<TacGia> update(@PathVariable String id, @Valid @RequestBody TacGiaDto dto,
                                          Principal principal){
        return  new ResponseEntity<>(tacGiaService.update(id, dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Tác giả")
    @DeleteMapping("/{id}")
    public ResponseEntity<TacGia> delete(@PathVariable String id, Principal principal){
        return  new ResponseEntity<>(tacGiaService.delete(id, principal), HttpStatus.OK);
    }
}
