package com.riadh.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.riadh.entity.Place;
import com.riadh.repository.PlaceRepository;

public class ApplicationInitilizeListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ApplicationInitilizeListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("initializing..");
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(sce.getServletContext());
        PlaceRepository placeRepository = ctx.getBean(PlaceRepository.class);
        placeRepository.deleteAll();
        List<Place> places = new ArrayList<Place>();

        int itemCount = 100;
        int chunkSize = 25;
        for (int i = 1; i <= itemCount; i++) {
            Place p = new Place();
            p.setDescription("place objet"+(i % 100) + 1);
            p.setName("objet" + i);
            places.add(p);
            if ((i % chunkSize) == 0) {
                placeRepository.save(places);
                places.clear();
            }
        }
        placeRepository.save(places);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
