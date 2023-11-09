package com.angularworkshop.app.service;

import com.angularworkshop.app.service.dto.VentaDTO;
import com.angularworkshop.app.service.helper.FilterHelper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.angularworkshop.app.domain.Venta}.
 */
public interface VentaService {
    /**
     * Save a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    VentaDTO save(VentaDTO ventaDTO);

    /**
     * Updates a venta.
     *
     * @param ventaDTO the entity to update.
     * @return the persisted entity.
     */
    VentaDTO update(VentaDTO ventaDTO);

    /**
     * Partially updates a venta.
     *
     * @param ventaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VentaDTO> partialUpdate(VentaDTO ventaDTO);

    /**
     * Get all the ventas.
     *
     * @param pageable the pagination information.
     * @param filter search filter.
     * @return the list of entities.
     */
    Page<VentaDTO> findAll(Pageable pageable, FilterHelper filter);

    /**
     * Get the "id" venta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VentaDTO> findOne(Long id);

    /**
     * Delete the "id" venta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the ventas by empleado.
     *
     * @param pageable the pagination information.
     * @param userLogin login user.
     * @return the list of entities.
     */
    Page<VentaDTO> findAllByEmpleadoUserLogin(Pageable pageable, String userLogin);
}
