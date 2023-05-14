package com.hlv.quanlisach.services.tacgia;

import com.hlv.quanlisach.dtos.tacgia.TacGiaDto;
import com.hlv.quanlisach.entities.TacGia;

import java.security.Principal;
import java.util.List;

public interface TacGiaService {

    List<TacGia> getAllTacGias();

    TacGia getTacGia(String id);

    TacGia create(TacGiaDto dto, Principal principal);

    TacGia update(String id, TacGiaDto dto, Principal principal);

    TacGia delete(String id, Principal principal);
}
