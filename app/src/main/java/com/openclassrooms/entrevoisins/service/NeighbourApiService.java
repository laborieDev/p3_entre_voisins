package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param position
     * @return {@link Neighbour}
     */
    Neighbour getNeighbour(int position);

    /**
     * Get all Favorites
     */
    List<Neighbour> getNeighboursFavorites();

    /**
     * Save changements of neighbour
     * @param neighbour
     */
    void setFavorites(Neighbour neighbour, boolean isFavorite);
}
