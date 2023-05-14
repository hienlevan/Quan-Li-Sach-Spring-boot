package com.hlv.quanlisach.dtos.tacgia;

import com.hlv.quanlisach.entities.Sach;
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
public class TacGiaDto {

    private String tenTacGia;
    private String email;
    private String diaChi;
    private String dienThoai;
    private List<String> listIdSach = new ArrayList<>();
}
