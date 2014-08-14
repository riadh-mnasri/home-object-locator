package com.riadh.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.riadh.entity.Place;

public interface PlaceRepositoryCustom {
    Page<Place> findByNameLike(String name, Pageable pageable);
}
