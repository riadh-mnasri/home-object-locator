package com.riadh.service.impl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.riadh.entity.Place;
import com.riadh.repository.PlaceRepository;
import com.riadh.service.PlaceService;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PlaceServiceImplTest {
    @Inject
    PlaceRepository placeRepository;
    @Inject
    PlaceService placeService;

    @Before
    public void setUp() throws Exception {
        placeRepository.deleteAll();
        for (int i = 1; i <= 20; i++) {
            Place p = new Place();
            p.setObjectName("object_name"+(i % 100) + 1);
            p.setPlaceDescription("place_description"+(i % 100) + 1);
            p.setPlaceName("place_name" + i);
            placeRepository.save(p);
        }
        placeRepository.flush();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindAll00() {
        Page<Place> p = placeService.findAll(0, 5);
        assertNotNull(p);
        List<Place> places = p.getContent();
        assertNotNull(places);
        assertEquals(5, places.size());
        assertEquals(5, p.getNumberOfElements());
        assertEquals(0, p.getNumber());
        assertEquals(5, p.getSize());
        assertEquals(4, p.getTotalPages());
        assertEquals(20, p.getTotalElements());
    }

    @Test
    public void testFindAll01() {
        Page<Place> p = placeService.findAll(1, 5);
        assertNotNull(p);
        List<Place> places = p.getContent();
        assertNotNull(places);
        assertEquals(5, places.size());
        assertEquals(5, p.getNumberOfElements());
        assertEquals(1, p.getNumber());
        assertEquals(5, p.getSize());
        assertEquals(4, p.getTotalPages());
        assertEquals(20, p.getTotalElements());
    }

    @Test
    public void testFindByNameLike() throws Exception {
        Page<Place> p = placeService.findByObjectNameLike("name1", 0, 5);
        System.out.println(p.getContent());
        assertNotNull(p);
        assertEquals(5, p.getNumberOfElements());
        assertEquals(0, p.getNumber());
        assertEquals(5, p.getSize());
        assertEquals(3, p.getTotalPages());
        assertEquals(11, p.getTotalElements());
    }

    @Test
    public void testFindById() {
        Place lastOne = placeService.findAll(0, 1).getContent().get(0);
        Integer id = lastOne.getId();
        Place p = placeService.findById(id);
        assertEquals(id, p.getId());
        assertEquals(lastOne.getObjectName(), p.getObjectName());
        assertEquals(lastOne.getPlaceDescription(), p.getPlaceDescription());
    }

    @Test
    public void testInsert() {
        Place lastOne = placeService.findAll(0, 1).getContent().get(0);

        Place p = new Place();
        p.setObjectName("object_name_insert");
        p.setPlaceName("place_name_insert");
        p.setPlaceDescription("description"+20);

        Place result = placeService.insert(p);
        placeRepository.flush();
        assertEquals(Integer.valueOf(lastOne.getId() + 1), result.getId());
    }

    @Test
    public void testUpdate() {
        Place lastOne = placeService.findAll(0, 1).getContent().get(0);
        {
            Place p = placeService.findById(lastOne.getId());
            p.setPlaceDescription("description"+30);
            p.setObjectName("object_name_update");
            p.setPlaceName("place_name_update");
            placeService.update(p);
        }
        placeRepository.flush();
        {
            Place p = placeService.findById(lastOne.getId());
            assertEquals("description"+30, p.getPlaceDescription());
            assertEquals("object_name_update", p.getObjectName());
        }
    }

    @Test
    public void testDeleteById() {
        Place lastOne = placeService.findAll(0, 1).getContent().get(0);
        placeService.deleteById(lastOne.getId());
        placeRepository.flush();
        Place p = placeService.findById(lastOne.getId());
        assertNull(p);
    }

}
