package com.hlv.quanlisach.dtos.sach;

import com.hlv.quanlisach.entities.TacGia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SachDto {
    private String tenSach;

    private String idTacGia;

    private String idTheLoai;

}
