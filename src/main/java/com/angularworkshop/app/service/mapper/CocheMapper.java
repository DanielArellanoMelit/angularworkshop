package com.angularworkshop.app.service.mapper;

import com.angularworkshop.app.domain.Coche;
import com.angularworkshop.app.service.dto.CocheDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Coche} and its DTO {@link CocheDTO}.
 */
@Mapper(componentModel = "spring")
public interface CocheMapper extends EntityMapper<CocheDTO, Coche> {}
