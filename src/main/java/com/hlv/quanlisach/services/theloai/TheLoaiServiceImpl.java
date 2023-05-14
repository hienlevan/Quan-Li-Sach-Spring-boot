package com.hlv.quanlisach.services.theloai;

import com.hlv.quanlisach.dtos.theloai.TheLoaiDto;
import com.hlv.quanlisach.entities.TheLoai;
import com.hlv.quanlisach.exceptions.InvalidException;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.theloai.TheLoaiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class TheLoaiServiceImpl implements TheLoaiService{

    private final TheLoaiRepository theLoaiRepository;

    public TheLoaiServiceImpl(TheLoaiRepository theLoaiRepository) {
        this.theLoaiRepository = theLoaiRepository;
    }

    @Override
    public List<TheLoai> getAllTheLoais() {
        return theLoaiRepository.findAll();
    }

    @Override
    public TheLoai getTheLoai(String id) {
        return theLoaiRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        String.format("Thể loại có id %s không tồn tại", id)
                ));
    }

    @Override
    public TheLoai create(TheLoaiDto dto, Principal principal) {
        if(ObjectUtils.isEmpty(dto.getTenTheLoai())){
            throw new InvalidException("Tên thể loại không được bỏ trống");
        }
        if(theLoaiRepository.kiemTraTenTheLoai(dto.getTenTheLoai().trim())){
            throw new InvalidException(String.format("Tên thể loại %s đã tồn tại",dto.getTenTheLoai()));
        }
        TheLoai theLoai = new TheLoai();
        theLoai.setTenTheLoai(dto.getTenTheLoai().trim());
        theLoai.setListIdSach(dto.getListIdSach());
        theLoaiRepository.save(theLoai);
        return theLoai;
    }

    @Override
    public TheLoai update(String id, TheLoaiDto dto, Principal principal) {
        TheLoai theLoai = getTheLoai(id);
        if(theLoai == null){
            throw new NotFoundException(String.format("Thể loại có id %s không tồn tại", id));
        }
        if(ObjectUtils.isEmpty(dto.getTenTheLoai())){
            throw new InvalidException("Tên thể loại không được bỏ trống");
        }
        if(!theLoai.getTenTheLoai().equalsIgnoreCase(dto.getTenTheLoai().trim())
        && theLoaiRepository.kiemTraTenTheLoai(dto.getTenTheLoai().trim())){
            throw new InvalidException(String.format("Tên thể loại có tên %s đã tồn tại",
                    dto.getTenTheLoai()));

        }

        theLoai.setTenTheLoai(dto.getTenTheLoai().trim());
        theLoai.setListIdSach(dto.getListIdSach());
        theLoaiRepository.save(theLoai);
        return theLoai;
    }

    @Override
    public TheLoai delete(String id, Principal principal) {
        TheLoai theLoai = getTheLoai(id);
        if(theLoai == null){
            throw new NotFoundException(String.format("Thể loại có id %s không tồn tại", id));
        }
        theLoaiRepository.delete(theLoai);
        return theLoai;
    }
}
