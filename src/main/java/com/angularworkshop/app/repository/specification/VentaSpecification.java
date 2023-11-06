package com.angularworkshop.app.repository.specification;

import com.angularworkshop.app.domain.Cliente;
import com.angularworkshop.app.domain.Coche;
import com.angularworkshop.app.domain.Empleado;
import com.angularworkshop.app.domain.Venta;
import com.angularworkshop.app.service.helper.FilterHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public interface VentaSpecification {
    public static Specification<Venta> busquedaVentas(FilterHelper filtro) {
        final Logger log = LoggerFactory.getLogger(VentaSpecification.class);
        return new Specification<Venta>() {
            private static final long serialVersionUID = 1L;

            @Override
            public javax.persistence.criteria.Predicate toPredicate(
                Root<Venta> venta,
                CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder
            ) {
                String[] busquedaListado = (null != filtro && null != filtro.getSearch() && !filtro.getSearch().trim().isEmpty())
                    ? filtro.getSearch().split(" ")
                    : new String[0];

                List<Predicate> organizador = new ArrayList<>();

                Join<Venta, Coche> coche = venta.join("coches");
                Expression<String> marcaCoche = coche.get("marca").as(String.class);
                Expression<String> modeloCoche = coche.get("modelo").as(String.class);
                Expression<String> colorCoche = coche.get("color").as(String.class);
                Expression<String> numeroSerieCoche = coche.get("numeroSerie").as(String.class);

                Join<Venta, Empleado> empleado = venta.join("empleado");
                Expression<String> dniEmpleado = empleado.get("dni").as(String.class);
                Expression<String> nombreEmpleado = empleado.get("nombre").as(String.class);

                Join<Venta, Cliente> cliente = venta.join("cliente");
                Expression<String> dniCliente = cliente.get("dni").as(String.class);
                Expression<String> nombreCliente = cliente.get("nombre").as(String.class);

                Expression<String> tipoPago = venta.get("tipoPago").as(String.class);
                Expression<String> total = venta.get("total").as(String.class);

                Expression<String> fechaString = criteriaBuilder.function(
                    "DATE_FORMAT",
                    String.class,
                    venta.get("fecha"),
                    criteriaBuilder.literal("%d/%m/%Y")
                );

                if (null != busquedaListado) {
                    for (int i = 0; i < busquedaListado.length; i++) {
                        String searchCriteria = busquedaListado[i].toLowerCase(Locale.getDefault());
                        List<Predicate> predicatesParametros = new ArrayList<>();

                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(tipoPago), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(total), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(fechaString), "%" + searchCriteria + "%"));

                        // Busqueda por coche
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(marcaCoche), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(modeloCoche), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(colorCoche), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(numeroSerieCoche), "%" + searchCriteria + "%"));

                        // Busqueda por empleado
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(dniEmpleado), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(nombreEmpleado), "%" + searchCriteria + "%"));

                        // Busqueda por cliente
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(dniCliente), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(nombreCliente), "%" + searchCriteria + "%"));

                        organizador.add(criteriaBuilder.or(predicatesParametros.toArray(new Predicate[] {})));
                    }
                }

                query.distinct(true);

                return criteriaBuilder.and(organizador.toArray(new Predicate[] {}));
            }
        };
    }
}
