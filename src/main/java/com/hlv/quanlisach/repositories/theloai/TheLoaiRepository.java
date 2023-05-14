package com.hlv.quanlisach.repositories.theloai;

import com.hlv.quanlisach.entities.TheLoai;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TheLoaiRepository extends MongoRepository<TheLoai,String> {

    @Query(value = "{'tenTheLoai':?0}")
    Optional<TheLoai> getTheLoaiByTenTheLoai(String tenTheLoai);

    Optional<TheLoai> findByTenTheLoaiIgnoreCase(String tenTheLoai);

    @Query(value = "{'tenTheLoai': ?0}", exists = true)
    boolean kiemTraTenTheLoai(String tenTheLoai);


}
