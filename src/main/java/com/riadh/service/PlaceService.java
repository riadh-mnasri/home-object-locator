package com.riadh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.riadh.entity.Place;

public interface PlaceService {
    Page<Place> findAll(int page, int size);

    Page<Place> findByObjectNameLike(String name, int page, int size);
    
    List<Place> findByObjectName(String name);

    Place findById(Integer id);

    Place insert(Place place);

    Place update(Place place);

    void deleteById(Integer id);

}
