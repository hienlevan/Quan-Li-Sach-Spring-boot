package com.hlv.quanlisach.dtos.nguoimuonsach;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NguoiMuonSachDto {

    private String tenNguoiMuon;

    private String email;
    private String diaChi;
    private String dienThoai;
}
