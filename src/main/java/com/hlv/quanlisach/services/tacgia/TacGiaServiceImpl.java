package com.hlv.quanlisach.services.tacgia;

import com.hlv.quanlisach.dtos.tacgia.TacGiaDto;
import com.hlv.quanlisach.entities.TacGia;
import com.hlv.quanlisach.exceptions.InvalidException;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.tacgia.TacGiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class TacGiaServiceImpl implements TacGiaService{

    private final TacGiaRepository tacGiaRepository;

    public TacGiaServiceImpl(TacGiaRepository tacGiaRepository) {
        this.tacGiaRepository = tacGiaRepository;
    }


    @Override
    public List<TacGia> getAllTacGias() {
        return tacGiaRepository.findAll();
    }

    @Override
    public TacGia getTacGia(String id) {
        return tacGiaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        String.format("Tác giả có id %s không tồn tại", id)
                ));
    }

    @Override
    public TacGia create(TacGiaDto dto, Principal principal) {
        if(ObjectUtils.isEmpty(dto.getTenTacGia())){
            throw new InvalidException("Tên Tác Giả không được bỏ trống");
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
        TacGia tacGia = new TacGia();
        tacGia.setTenTacGia(dto.getTenTacGia());
        tacGia.setEmail(dto.getEmail());
        tacGia.setDiaChi(dto.getDiaChi());
        tacGia.setDienThoai(dto.getDienThoai());
        tacGia.setListIdSach(dto.getListIdSach());
        tacGiaRepository.save(tacGia);
        return tacGia;
    }

    @Override
    public TacGia update(String id, TacGiaDto dto, Principal principal) {
        TacGia tacGia = getTacGia(id);
        if(tacGia == null){
            throw new NotFoundException(String.format("Tác Giả có id %s không tồn tại", id));
        }
        if(ObjectUtils.isEmpty(dto.getTenTacGia())){
            throw new InvalidException("Tên Tác Giả không được bỏ trống");
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
        tacGia.setTenTacGia(dto.getTenTacGia());
        tacGia.setEmail(dto.getEmail());
        tacGia.setDiaChi(dto.getDiaChi());
        tacGia.setDienThoai(dto.getDienThoai());
        tacGiaRepository.save(tacGia);
        tacGia.setListIdSach(dto.getListIdSach());
        return tacGia;
    }

    @Override
    public TacGia delete(String id, Principal principal) {
        TacGia tacGia=getTacGia(id);
        if(tacGia == null){
            throw new NotFoundException(String.format("Tác Giả có id %s không tồn tại", id));
        }
        tacGiaRepository.delete(tacGia);
        return tacGia;
    }
}
