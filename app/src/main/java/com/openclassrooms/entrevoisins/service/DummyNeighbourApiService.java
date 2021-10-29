package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    public Neighbour getNeighbour(int position)
    {
        return neighbours.get(position);
    }

    /**
     * Get all Favorites
     */
    public List<Neighbour> getNeighboursFavorites() {
        List<Neighbour> favoritesList = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getIsFavorite() && !favoritesList.contains(neighbour)) {
                favoritesList.add(neighbour);
            }
        }

        return favoritesList;
    }

    /**
     * Set Favorite Neighbour
     */
    public void setFavorites(Neighbour neighbour, boolean isFavorite)
    {
        int position = neighbours.indexOf(neighbour);
        neighbour.setIsFavorite(isFavorite);
        neighbours.set(position, neighbour);
    }
}
