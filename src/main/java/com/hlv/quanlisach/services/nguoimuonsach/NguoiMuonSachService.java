package com.hlv.quanlisach.services.nguoimuonsach;

import com.hlv.quanlisach.dtos.nguoimuonsach.NguoiMuonSachDto;
import com.hlv.quanlisach.entities.NguoiMuonSach;
import java.security.Principal;
import java.util.List;

public interface NguoiMuonSachService {

    List<NguoiMuonSach> getAllNguoiMuonSachs();

    NguoiMuonSach getNguoiMuonSach(String id);

    NguoiMuonSach create(NguoiMuonSachDto dto, Principal principal);

    NguoiMuonSach update(String id,NguoiMuonSachDto dto, Principal principal);

    NguoiMuonSach delete(String id, Principal principal);
}
