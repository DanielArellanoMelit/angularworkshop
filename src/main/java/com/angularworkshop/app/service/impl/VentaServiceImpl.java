package com.angularworkshop.app.service.impl;

import com.angularworkshop.app.domain.Coche;
import com.angularworkshop.app.domain.Venta;
import com.angularworkshop.app.repository.CocheRepository;
import com.angularworkshop.app.repository.VentaRepository;
import com.angularworkshop.app.repository.specification.VentaSpecification;
import com.angularworkshop.app.service.VentaService;
import com.angularworkshop.app.service.dto.VentaDTO;
import com.angularworkshop.app.service.helper.FilterHelper;
import com.angularworkshop.app.service.mapper.VentaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Venta}.
 */
@Service
@Transactional
public class VentaServiceImpl implements VentaService {

    private final Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);

    private final VentaRepository ventaRepository;

    private final VentaMapper ventaMapper;

    private final CocheRepository cocheRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, VentaMapper ventaMapper, CocheRepository cocheRepository) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
        this.cocheRepository = cocheRepository;
    }

    @Override
    public VentaDTO save(VentaDTO ventaDTO) {
        log.debug("Request to save Venta : {}", ventaDTO);
        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta = ventaRepository.save(venta);
        Coche coche = venta.getCoches();
        if (null != coche) {
            coche.setExposicion(false);
            cocheRepository.save(coche);
        }
        return ventaMapper.toDto(venta);
    }

    @Override
    public VentaDTO update(VentaDTO ventaDTO) {
        log.debug("Request to update Venta : {}", ventaDTO);
        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta = ventaRepository.save(venta);
        Coche coche = venta.getCoches();
        if (null != coche) {
            coche.setExposicion(false);
            cocheRepository.save(coche);
        }
        return ventaMapper.toDto(venta);
    }

    @Override
    public Optional<VentaDTO> partialUpdate(VentaDTO ventaDTO) {
        log.debug("Request to partially update Venta : {}", ventaDTO);

        return ventaRepository
            .findById(ventaDTO.getId())
            .map(existingVenta -> {
                ventaMapper.partialUpdate(existingVenta, ventaDTO);

                return existingVenta;
            })
            .map(ventaRepository::save)
            .map(ventaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaDTO> findAll(Pageable pageable, FilterHelper filter) {
        log.debug("Request to get all Ventas");
        return ventaRepository.findAll(VentaSpecification.busquedaVentas(filter), pageable).map(ventaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VentaDTO> findOne(Long id) {
        log.debug("Request to get Venta : {}", id);
        return ventaRepository.findById(id).map(ventaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venta : {}", id);
        ventaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaDTO> findAllByEmpleadoUserLogin(Pageable pageable, String userLogin) {
        log.debug("Request to get all Ventas");
        return ventaRepository.findAllByEmpleadoUserLogin(pageable, userLogin).map(ventaMapper::toDto);
    }
}
