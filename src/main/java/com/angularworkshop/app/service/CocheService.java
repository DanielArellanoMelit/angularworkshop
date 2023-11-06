package com.angularworkshop.app.service;

import com.angularworkshop.app.service.dto.CocheDTO;
import com.angularworkshop.app.service.helper.FilterHelper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.angularworkshop.app.domain.Coche}.
 */
public interface CocheService {
    /**
     * Save a coche.
     *
     * @param cocheDTO the entity to save.
     * @return the persisted entity.
     */
    CocheDTO save(CocheDTO cocheDTO);

    /**
     * Updates a coche.
     *
     * @param cocheDTO the entity to update.
     * @return the persisted entity.
     */
    CocheDTO update(CocheDTO cocheDTO);

    /**
     * Partially updates a coche.
     *
     * @param cocheDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CocheDTO> partialUpdate(CocheDTO cocheDTO);

    /**
     * Get all the coches.
     *
     * @param pageable the pagination information.
     * @param filtro search filter
     * @return the list of entities.
     */
    Page<CocheDTO> findAll(Pageable pageable, FilterHelper filter);

    /**
     * Get the "id" coche.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CocheDTO> findOne(Long id);

    /**
     * Delete the "id" coche.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
