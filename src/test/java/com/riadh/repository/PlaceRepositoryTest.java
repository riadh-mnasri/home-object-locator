package com.riadh.repository;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.riadh.entity.Place;
import com.riadh.repository.PlaceRepository;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PlaceRepositoryTest {
    @Inject
    PlaceRepository placeRepository;

    @Before
    public void setUp() {
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

    @Test
    public void testCount() {
        assertEquals(20, placeRepository.count());
    }

    @Test
    public void testFindByName() {
        Page<Place> p = placeRepository.findByObjectNameLike("%name1%", new PageRequest(
                0, 5));
        System.out.println(p.getContent());
        assertNotNull(p);
        assertEquals(5, p.getNumberOfElements());
        assertEquals(0, p.getNumber());
        assertEquals(5, p.getSize());
        assertEquals(3, p.getTotalPages());
        assertEquals(11, p.getTotalElements());
    }

}
