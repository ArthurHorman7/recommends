package com.api.recommends.services;

import com.api.recommends.models.RecommendsModel;
import com.api.recommends.repositories.RecommendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendsService {

    @Autowired
    private RecommendsRepository recommendsRepository;

    public Optional<RecommendsModel> pesquisarPorNome(String image) {
        return recommendsRepository.findByImage(image);
    }
}
