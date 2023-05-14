package com.hlv.quanlisach.services.nguoimuonsach;

import com.hlv.quanlisach.dtos.nguoimuonsach.NguoiMuonSachDto;
import com.hlv.quanlisach.entities.NguoiMuonSach;
import com.hlv.quanlisach.exceptions.InvalidException;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.nguoimuonsach.NguoiMuonSachRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class NguoiMuonSachServiceImpl implements NguoiMuonSachService{

    private final NguoiMuonSachRepository nguoiMuonSachRepository;

    public NguoiMuonSachServiceImpl(NguoiMuonSachRepository nguoiMuonSachRepository) {
        this.nguoiMuonSachRepository = nguoiMuonSachRepository;
    }

    @Override
    public List<NguoiMuonSach> getAllNguoiMuonSachs() {
        return nguoiMuonSachRepository.findAll();
    }

    @Override
    public NguoiMuonSach getNguoiMuonSach(String id) {
        return nguoiMuonSachRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Người mượn sách có id %s không tồn tại")
                ));
    }

    @Override
    public NguoiMuonSach create(NguoiMuonSachDto dto, Principal principal) {
        if(ObjectUtils.isEmpty(dto.getTenNguoiMuon())){
            throw new InvalidException("Tên người mượn sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getEmail())){
            throw new InvalidException("Email không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getDiaChi())){
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getDienThoai())){
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        NguoiMuonSach nguoiMuonSach = new NguoiMuonSach();
        nguoiMuonSach.setTenNguoiMuon(dto.getTenNguoiMuon());
        nguoiMuonSach.setEmail(dto.getEmail());
        nguoiMuonSach.setDiaChi(dto.getDiaChi());
        nguoiMuonSach.setDienThoai(dto.getDienThoai());
        nguoiMuonSachRepository.save(nguoiMuonSach);
        return nguoiMuonSach;
    }

    @Override
    public NguoiMuonSach update(String id, NguoiMuonSachDto dto, Principal principal) {

        NguoiMuonSach nguoiMuonSach = getNguoiMuonSach(id);
        if(nguoiMuonSach == null){
            throw new NotFoundException(String.format("Người mượn sách có id %s không tồn tại", id));
        }
        if(ObjectUtils.isEmpty(dto.getTenNguoiMuon())){
            throw new InvalidException("Tên người mượn sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getEmail())){
            throw new InvalidException("Email không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getDiaChi())){
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getDienThoai())){
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        nguoiMuonSach.setTenNguoiMuon(dto.getTenNguoiMuon());
        nguoiMuonSach.setEmail(dto.getEmail());
        nguoiMuonSach.setDiaChi(dto.getDiaChi());
        nguoiMuonSach.setDienThoai(dto.getDienThoai());
        nguoiMuonSachRepository.save(nguoiMuonSach);
        return nguoiMuonSach;
    }

    @Override
    public NguoiMuonSach delete(String id, Principal principal) {
        NguoiMuonSach nguoiMuonSach = getNguoiMuonSach(id);
        if(nguoiMuonSach == null){
            throw new NotFoundException(String.format("Người mượn sách có id %s không tồn tại", id));
        }
        nguoiMuonSachRepository.delete(nguoiMuonSach);
        return nguoiMuonSach;
    }
}
