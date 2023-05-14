package com.hlv.quanlisach.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "muonsach")
public class MuonSach {

    @Id
    private String id;

    private String idNguoiMuon;
    private String idSach;
}
