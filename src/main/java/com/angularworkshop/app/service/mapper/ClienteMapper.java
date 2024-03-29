package com.angularworkshop.app.service.mapper;

import com.angularworkshop.app.domain.Cliente;
import com.angularworkshop.app.service.dto.ClienteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cliente} and its DTO {@link ClienteDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {}
