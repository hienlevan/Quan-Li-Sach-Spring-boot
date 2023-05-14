package com.hlv.quanlisach.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "theloai")
public class TheLoai {

    @Id
    private String id;

    private String tenTheLoai;

    private List<String> listIdSach =  new ArrayList<>();
}
