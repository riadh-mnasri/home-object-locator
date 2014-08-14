package com.riadh.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riadh.entity.Place;
import com.riadh.repository.PlaceRepository;
import com.riadh.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Inject
    protected PlaceRepository placeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Place> findAll(int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
        Page<Place> places = placeRepository.findAll(pageable);
        return places;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Place> findByNameLike(String name, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
        String q = "%" + name + "%";
        Page<Place> places = placeRepository.findByNameLike(q, pageable);
        return places;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Place> findByName(String name) {
    	List<Place> places = placeRepository.findByName(name);
        return places;
    }

    @Override
    @Transactional(readOnly = true)
    public Place findById(Integer id) {
        Place place = placeRepository.findOne(id);
        return place;
    }

    @Override
    @Transactional
    public Place insert(Place place) {
        return placeRepository.save(place);
    }

    @Override
    @Transactional
    public Place update(Place place) {
        return placeRepository.save(place);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        placeRepository.delete(id);
    }

}
