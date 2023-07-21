package com.api.recommends.repositories;

import com.api.recommends.models.RecommendsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecommendsRepository extends JpaRepository<RecommendsModel, UUID> {
    Optional<RecommendsModel> findByImage(String image);
}
