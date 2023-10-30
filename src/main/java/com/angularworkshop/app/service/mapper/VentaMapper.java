package com.angularworkshop.app.service.mapper;

import com.angularworkshop.app.domain.Cliente;
import com.angularworkshop.app.domain.Coche;
import com.angularworkshop.app.domain.Empleado;
import com.angularworkshop.app.domain.Venta;
import com.angularworkshop.app.service.dto.ClienteDTO;
import com.angularworkshop.app.service.dto.CocheDTO;
import com.angularworkshop.app.service.dto.EmpleadoDTO;
import com.angularworkshop.app.service.dto.VentaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venta} and its DTO {@link VentaDTO}.
 */
@Mapper(componentModel = "spring")
public interface VentaMapper extends EntityMapper<VentaDTO, Venta> {
    @Mapping(target = "coches", source = "coches", qualifiedByName = "cocheId")
    @Mapping(target = "empleado", source = "empleado", qualifiedByName = "empleadoId")
    @Mapping(target = "cliente", source = "cliente", qualifiedByName = "clienteId")
    VentaDTO toDto(Venta s);

    @Named("cocheId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CocheDTO toDtoCocheId(Coche coche);

    @Named("empleadoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmpleadoDTO toDtoEmpleadoId(Empleado empleado);

    @Named("clienteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClienteDTO toDtoClienteId(Cliente cliente);
}
