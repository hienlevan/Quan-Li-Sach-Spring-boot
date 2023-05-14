package com.hlv.quanlisach.services.muonsach;

import com.hlv.quanlisach.dtos.muonsach.MuonSachDto;
import com.hlv.quanlisach.entities.MuonSach;
import com.hlv.quanlisach.entities.NguoiMuonSach;
import com.hlv.quanlisach.entities.Sach;
import com.hlv.quanlisach.exceptions.InvalidException;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.muonsach.MuonSachRepository;
import com.hlv.quanlisach.services.nguoimuonsach.NguoiMuonSachService;
import com.hlv.quanlisach.services.sach.SachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class MuonSachServiceImpl implements MuonSachService{

    private final MuonSachRepository muonSachRepository;

    private final SachService sachService;
    private final NguoiMuonSachService nguoiMuonSachService;

    public MuonSachServiceImpl(MuonSachRepository muonSachRepository, SachService sachService, NguoiMuonSachService nguoiMuonSachService) {
        this.muonSachRepository = muonSachRepository;
        this.sachService = sachService;
        this.nguoiMuonSachService = nguoiMuonSachService;
    }

    @Override
    public List<MuonSach> getAllMuonSachs() {
        return muonSachRepository.findAll();
    }

    @Override
    public MuonSach getMuonSach(String id) {
        return muonSachRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Mượn sách có id %s không tồn tại", id)
                ));
    }

    @Override
    public MuonSach create(MuonSachDto dto, Principal principal) {
        if(ObjectUtils.isEmpty(dto.getIdSach())){
            throw new InvalidException("Id Sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdNguoiMuon())){
            throw new InvalidException("Id Người mượn sách không được bỏ trống");
        }
        Sach sach = sachService.getSach(dto.getIdSach());
        NguoiMuonSach nguoiMuonSach = nguoiMuonSachService.getNguoiMuonSach(dto.getIdNguoiMuon());
        if(sach == null){
            throw new NotFoundException(String.format("Sách có id %s không tồn tại"));
        }
        if(nguoiMuonSach == null){
            throw new NotFoundException(String.format("Người mượn sách có id %s không tồn tại"));
        }
        MuonSach muonSach = new MuonSach();
        muonSach.setIdSach(dto.getIdSach());
        muonSach.setIdNguoiMuon(dto.getIdNguoiMuon());
        muonSachRepository.save(muonSach);
        return muonSach;
    }

    @Override
    public MuonSach update(String id, MuonSachDto dto, Principal principal) {
        MuonSach muonSach = getMuonSach(id);
        if(muonSach == null){
            throw new NotFoundException(String.format("Mượn sách có Id %s không tồn tại"));
        }
        if(ObjectUtils.isEmpty(dto.getIdSach())){
            throw new InvalidException("Id Sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdNguoiMuon())){
            throw new InvalidException("Id Người mượn sách không được bỏ trống");
        }
        Sach sach = sachService.getSach(dto.getIdSach());
        NguoiMuonSach nguoiMuonSach = nguoiMuonSachService.getNguoiMuonSach(dto.getIdNguoiMuon());
        if(sach == null){
            throw new NotFoundException(String.format("Sách có id %s không tồn tại"));
        }
        if(nguoiMuonSach == null){
            throw new NotFoundException(String.format("Người mượn sách có id %s không tồn tại"));
        }
        muonSach.setIdSach(dto.getIdSach());
        muonSach.setIdNguoiMuon(dto.getIdNguoiMuon());
        muonSachRepository.save(muonSach);
        return muonSach;
    }

    @Override
    public MuonSach delete(String id, Principal principal) {
        MuonSach muonSach = getMuonSach(id);
        if(muonSach == null){
            throw new NotFoundException(String.format("Mượn sách có Id %s không tồn tại"));
        }
        muonSachRepository.delete(muonSach);
        return muonSach;
    }
}
