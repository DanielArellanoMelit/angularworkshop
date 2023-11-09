package com.angularworkshop.app.service.impl;

import com.angularworkshop.app.domain.Coche;
import com.angularworkshop.app.repository.CocheRepository;
import com.angularworkshop.app.repository.specification.CocheSpecification;
import com.angularworkshop.app.service.CocheService;
import com.angularworkshop.app.service.dto.CocheDTO;
import com.angularworkshop.app.service.helper.FilterHelper;
import com.angularworkshop.app.service.mapper.CocheMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coche}.
 */
@Service
@Transactional
public class CocheServiceImpl implements CocheService {

    private final Logger log = LoggerFactory.getLogger(CocheServiceImpl.class);

    private final CocheRepository cocheRepository;

    private final CocheMapper cocheMapper;

    public CocheServiceImpl(CocheRepository cocheRepository, CocheMapper cocheMapper) {
        this.cocheRepository = cocheRepository;
        this.cocheMapper = cocheMapper;
    }

    @Override
    public CocheDTO save(CocheDTO cocheDTO) {
        log.debug("Request to save Coche : {}", cocheDTO);
        Coche coche = cocheMapper.toEntity(cocheDTO);
        coche.setExposicion(true);
        coche = cocheRepository.save(coche);
        return cocheMapper.toDto(coche);
    }

    @Override
    public CocheDTO update(CocheDTO cocheDTO) {
        log.debug("Request to update Coche : {}", cocheDTO);
        Coche coche = cocheMapper.toEntity(cocheDTO);
        coche = cocheRepository.save(coche);
        return cocheMapper.toDto(coche);
    }

    @Override
    public Optional<CocheDTO> partialUpdate(CocheDTO cocheDTO) {
        log.debug("Request to partially update Coche : {}", cocheDTO);

        return cocheRepository
            .findById(cocheDTO.getId())
            .map(existingCoche -> {
                cocheMapper.partialUpdate(existingCoche, cocheDTO);

                return existingCoche;
            })
            .map(cocheRepository::save)
            .map(cocheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CocheDTO> findAll(Pageable pageable, FilterHelper filter) {
        log.debug("Request to get all Coches");
        return cocheRepository.findAll(CocheSpecification.busquedaCoches(filter), pageable).map(cocheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CocheDTO> findOne(Long id) {
        log.debug("Request to get Coche : {}", id);
        return cocheRepository.findById(id).map(cocheMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coche : {}", id);
        cocheRepository.deleteById(id);
    }
}
