package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getSingleNeighbour() {
        List<Neighbour> allNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        Neighbour neighbour = service.getNeighbour(0);
        assertEquals(allNeighbours.get(0), neighbour);
    }

    @Test
    public void putNeighboursOnFavorites() {
        List<Neighbour> allNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        Neighbour neighbour = allNeighbours.get(0);

        service.setFavorites(neighbour, true);
        List<Neighbour> allFavoritesNeighbours = service.getNeighboursFavorites();
        assertTrue(allFavoritesNeighbours.contains(neighbour));
    }

    @Test
    public void removeNeighbourOnFavorites() {
        List<Neighbour> allFavoritesNeighbours = service.getNeighboursFavorites();
        Neighbour neighbour = allFavoritesNeighbours.get(0);

        service.setFavorites(neighbour, false);
        allFavoritesNeighbours = service.getNeighboursFavorites();
        assertFalse(allFavoritesNeighbours.contains(neighbour));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour newNeighbour = new Neighbour(
                999,
                "Name",
                "https://hatrabbits.com/wp-content/uploads/2017/01/random.jpg",
                "Address ZIPCODE City",
                "0606060606",
                "About Me !",
                false
        );

        service.createNeighbour(newNeighbour);

        List<Neighbour> allNeighbours = service.getNeighbours();

        assertEquals(newNeighbour, allNeighbours.get(allNeighbours.size() - 1));
    }
}
