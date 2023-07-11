package com.api.recommends.controllers;

import com.api.recommends.dtos.RecommendsRecordDto;
import com.api.recommends.models.RecommendsModel;
import com.api.recommends.repositories.RecommendsRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RecommendsController {

    @Autowired
    private RecommendsRepository recommendsRepository;

    @PostMapping("/animes")
    public ResponseEntity<RecommendsModel> saveAnime(@RequestBody @Valid RecommendsRecordDto recommendsRecordDto) {
        var recommendsModelValor = new RecommendsModel();
        BeanUtils.copyProperties(recommendsRecordDto, recommendsModelValor);
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendsModelValor);
    }


}
