package com.hlv.quanlisach.services.muonsach;

import com.hlv.quanlisach.dtos.muonsach.MuonSachDto;
import com.hlv.quanlisach.entities.MuonSach;

import java.security.Principal;
import java.util.List;

public interface MuonSachService {
    List<MuonSach> getAllMuonSachs();

    MuonSach getMuonSach(String id);

    MuonSach create(MuonSachDto dto, Principal principal);

    MuonSach update(String id,MuonSachDto dto, Principal principal);

    MuonSach delete(String id, Principal principal);
}
