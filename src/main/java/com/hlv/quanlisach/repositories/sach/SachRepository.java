package com.hlv.quanlisach.repositories.sach;

import com.hlv.quanlisach.entities.Sach;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SachRepository extends MongoRepository<Sach, String> {
}
