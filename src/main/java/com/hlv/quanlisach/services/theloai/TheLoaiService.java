package com.hlv.quanlisach.services.theloai;

import com.hlv.quanlisach.dtos.theloai.TheLoaiDto;
import com.hlv.quanlisach.entities.TheLoai;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface TheLoaiService {
    List<TheLoai> getAllTheLoais();

    TheLoai getTheLoai(String id);

    TheLoai create(TheLoaiDto dto, Principal principal);
    TheLoai update(String id, TheLoaiDto dto, Principal principal);

    TheLoai delete(String id, Principal principal);

}
