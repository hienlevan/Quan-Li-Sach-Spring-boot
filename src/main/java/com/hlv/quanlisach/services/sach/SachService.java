package com.hlv.quanlisach.services.sach;

import com.hlv.quanlisach.dtos.sach.SachDto;
import com.hlv.quanlisach.entities.Sach;

import java.security.Principal;
import java.util.List;

public interface SachService {

    List<Sach> getAllSachs();

    Sach getSach(String id);

    Sach create(SachDto dto, Principal principal);

    Sach update(String id,SachDto dto, Principal principal);

    Sach delete(String id, Principal principal);
}
