package com.hlv.quanlisach.services.sach;

import com.hlv.quanlisach.dtos.sach.SachDto;
import com.hlv.quanlisach.entities.Sach;
import com.hlv.quanlisach.entities.TacGia;
import com.hlv.quanlisach.entities.TheLoai;
import com.hlv.quanlisach.exceptions.InvalidException;
import com.hlv.quanlisach.exceptions.NotFoundException;
import com.hlv.quanlisach.repositories.sach.SachRepository;
import com.hlv.quanlisach.repositories.tacgia.TacGiaRepository;
import com.hlv.quanlisach.repositories.theloai.TheLoaiRepository;
import com.hlv.quanlisach.services.tacgia.TacGiaService;
import com.hlv.quanlisach.services.theloai.TheLoaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SachServiceImpl implements SachService{

    private final SachRepository sachRepository;
    private  final TheLoaiService theLoaiService;
    private final TacGiaService tacGiaService;

    private final TacGiaRepository tacGiaRepository;

    private final TheLoaiRepository theLoaiRepository;

    public SachServiceImpl(SachRepository sachRepository, TheLoaiService theLoaiService, TacGiaService tacGiaService, TacGiaRepository tacGiaRepository, TheLoaiRepository theLoaiRepository) {
        this.sachRepository = sachRepository;
        this.theLoaiService = theLoaiService;
        this.tacGiaService = tacGiaService;
        this.tacGiaRepository = tacGiaRepository;
        this.theLoaiRepository = theLoaiRepository;
    }


    @Override
    public List<Sach> getAllSachs() {
        return sachRepository.findAll();
    }

    @Override
    public Sach getSach(String id) {
        return sachRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Sách có id %s không tồn tại", id)
                ));
    }

    @Override
    public Sach create(SachDto dto, Principal principal) {
        TheLoai theLoai = theLoaiService.getTheLoai(dto.getIdTheLoai());
        TacGia tacGia = tacGiaService.getTacGia(dto.getIdTacGia());
        if(ObjectUtils.isEmpty(dto.getTenSach())){
            throw new InvalidException("Tên Sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdTacGia())){
            throw new InvalidException("Id tác giả không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdTheLoai())){
            throw new InvalidException("Id thể loại không được bỏ trống");
        }
        if(theLoai == null){
            throw new InvalidException(String.format("Thể Loại có id %s không tồn tại", dto.getIdTheLoai()));
        }
        if( tacGia == null){
            throw new InvalidException(String.format("Tác giả có id %s không tồn tại", dto.getIdTacGia()));
        }
        Sach sach = new Sach();
        sach.setTenSach(dto.getTenSach());
        sach.setIdTacGia(dto.getIdTacGia());
        sach.setIdTheLoai(dto.getIdTheLoai());

        Sach saveSach = sachRepository.save(sach);

        List<String> listIdSachOfTheLoai = theLoai.getListIdSach();
        if (listIdSachOfTheLoai == null) {
            listIdSachOfTheLoai = new ArrayList<>();
        }
        listIdSachOfTheLoai.add(saveSach.getId());
        theLoai.setListIdSach(listIdSachOfTheLoai);
        theLoaiRepository.save(theLoai);

        List<String> listIdSachOfTacGia = tacGia.getListIdSach();
        if(listIdSachOfTacGia == null){
            listIdSachOfTacGia = new ArrayList<>();
        }
        listIdSachOfTacGia.add(saveSach.getId());
        tacGia.setListIdSach(listIdSachOfTacGia);
        tacGiaRepository.save(tacGia);
        return saveSach;
    }

    @Override
    public Sach update(String id, SachDto dto, Principal principal) {
        Sach sach =getSach(id);
        TheLoai theLoai = theLoaiService.getTheLoai(dto.getIdTheLoai());
        TacGia tacGia = tacGiaService.getTacGia(dto.getIdTacGia());
        if (sach == null) {
            throw new InvalidException(String.format("Sách có id %s không tồn tại", id));
        }
        if(ObjectUtils.isEmpty(dto.getTenSach())){
            throw new InvalidException("Tên Sách không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdTacGia())){
            throw new InvalidException("Id tác giả không được bỏ trống");
        }
        if(ObjectUtils.isEmpty(dto.getIdTheLoai())){
            throw new InvalidException("Id thể loại không được bỏ trống");
        }
        if(theLoai == null){
            throw new InvalidException(String.format("Thể Loại có id %s không tồn tại", dto.getIdTheLoai()));
        }
        if( tacGia == null){
            throw new InvalidException(String.format("Tác giả có id %s không tồn tại", dto.getIdTacGia()));
        }

        TheLoai oldTheLoai = theLoaiService.getTheLoai(sach.getIdTheLoai());
        if (oldTheLoai != null) {
            List<String> listIdSachOfOldTheLoai = oldTheLoai.getListIdSach();
            if (listIdSachOfOldTheLoai != null) {
                listIdSachOfOldTheLoai.remove(sach.getId());
                oldTheLoai.setListIdSach(listIdSachOfOldTheLoai);
                theLoaiRepository.save(oldTheLoai);
            }
        }

        TacGia oldTacGia = tacGiaService.getTacGia(sach.getIdTacGia());
        if (oldTacGia != null) {
            List<String> listIdSachOfOldTacGia = oldTacGia.getListIdSach();
            if (listIdSachOfOldTacGia != null) {
                listIdSachOfOldTacGia.remove(sach.getId());
                oldTacGia.setListIdSach(listIdSachOfOldTacGia);
                tacGiaRepository.save(oldTacGia);
            }
        }

        sach.setTenSach(dto.getTenSach());
        sach.setIdTacGia(dto.getIdTacGia());
        sach.setIdTheLoai(dto.getIdTheLoai());

        Sach savedSach = sachRepository.save(sach);

        List<String> listIdSachOfTheLoai = theLoai.getListIdSach();
        if (listIdSachOfTheLoai == null) {
            listIdSachOfTheLoai = new ArrayList<>();
        }
        listIdSachOfTheLoai.add(savedSach.getId());
        theLoai.setListIdSach(listIdSachOfTheLoai);
        theLoaiRepository.save(theLoai);

        List<String> listIdSachOfTacGia = tacGia.getListIdSach();
        if(listIdSachOfTacGia == null){
            listIdSachOfTacGia = new ArrayList<>();
        }
        listIdSachOfTacGia.add(savedSach.getId());
        tacGia.setListIdSach(listIdSachOfTacGia);
        tacGiaRepository.save(tacGia);
        return savedSach;
    }

    @Override
    public Sach delete(String id, Principal principal) {
        Sach sach = getSach(id);
        if (sach == null) {
            throw new InvalidException(String.format("Sách có id %s không tồn tại", id));
        }
        TheLoai theLoai = theLoaiService.getTheLoai(sach.getIdTheLoai());
        if (theLoai != null) {
            List<String> listIdSachOfTheLoai = theLoai.getListIdSach();
            if (listIdSachOfTheLoai != null) {
                listIdSachOfTheLoai.remove(sach.getId());
                theLoai.setListIdSach(listIdSachOfTheLoai);
                theLoaiRepository.save(theLoai);
            }
        }

        TacGia tacGia = tacGiaService.getTacGia(sach.getIdTacGia());
        if (tacGia != null) {
            List<String> listIdSachOfTacGia = tacGia.getListIdSach();
            if (listIdSachOfTacGia != null) {
                listIdSachOfTacGia.remove(sach.getId());
                tacGia.setListIdSach(listIdSachOfTacGia);
                tacGiaRepository.save(tacGia);
            }
        }
        sachRepository.delete(sach);
        return sach;
    }
}
