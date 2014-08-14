package com.riadh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riadh.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Page<Place> findByNameLike(String name, Pageable pageable);
    List<Place> findByName(String name);
}
