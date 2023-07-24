package com.api.recommends.controllers;

import com.api.recommends.dtos.RecommendsRecordDto;
import com.api.recommends.models.RecommendsModel;
import com.api.recommends.repositories.RecommendsRepository;
import com.api.recommends.services.RecommendsService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@AllArgsConstructor
public class RecommendsController {

    private final RecommendsRepository recommendsRepository;
    private final RecommendsService recommendsService;


    @PostMapping("/animes")
    public ResponseEntity<RecommendsModel> saveRecommends(@RequestBody @Valid RecommendsRecordDto recommendsRecordDto) {
        var AnimeSave = new RecommendsModel();
        BeanUtils.copyProperties(recommendsRecordDto, AnimeSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendsRepository.save(AnimeSave));
    }

    @GetMapping("/animes")
    public ResponseEntity<List<RecommendsModel>> getAllRecommends(){
        List<RecommendsModel> AnimeList = recommendsRepository.findAll();

        return  ResponseEntity.status(HttpStatus.OK).body(AnimeList);
    }

    @GetMapping("/animes/id/{id}")
    public ResponseEntity<Object> getOneRecommends(@PathVariable(value = "id")UUID id) {
        Optional<RecommendsModel> AnimeId = recommendsRepository.findById(id);
        if (AnimeId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(AnimeId.get());
    }

    @GetMapping("/animes/name/{name}")
    public ResponseEntity<Object> getOneRecommendsForName(@PathVariable(value = "name") String name) {
        var imageName = Normalizer.normalize(String.format("%s.jpg", name.toLowerCase().replace(' ', '-')), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Optional<RecommendsModel> AnimeName = recommendsRepository.findByImage(imageName);
        if (AnimeName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(AnimeName.get());
    }

    @PutMapping("/animes/{id}")
    public ResponseEntity<Object> updateRecommends(@PathVariable(value = "id")UUID id,
                                                   @RequestBody @Valid RecommendsRecordDto recommendsRecordDto) {
        Optional<RecommendsModel> AnimeId = recommendsRepository.findById(id);
        if (AnimeId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime not found.");
        }
        var animeModel = AnimeId.get();
        BeanUtils.copyProperties(recommendsRecordDto, animeModel);
        return ResponseEntity.status(HttpStatus.OK).body(recommendsRepository.save(animeModel));
    }

    @DeleteMapping("/animes/{id}")
    public ResponseEntity<Object> deleteRecommends(@PathVariable(value = "id")UUID id) {

        Optional<RecommendsModel> AnimeDelete = recommendsRepository.findById(id);
        if (AnimeDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime not found.");
        }
        recommendsRepository.delete(AnimeDelete.get());
        return ResponseEntity.status(HttpStatus.OK).body("Anime deleted successfully.");
    }

}
