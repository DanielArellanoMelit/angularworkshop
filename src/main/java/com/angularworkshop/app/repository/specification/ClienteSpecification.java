package com.angularworkshop.app.repository.specification;

import com.angularworkshop.app.domain.Cliente;
import com.angularworkshop.app.service.helper.FilterHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public interface ClienteSpecification {
    public static Specification<Cliente> busquedaClientes(FilterHelper filtro) {
        final Logger log = LoggerFactory.getLogger(ClienteSpecification.class);
        return new Specification<Cliente>() {
            private static final long serialVersionUID = 1L;

            @Override
            public javax.persistence.criteria.Predicate toPredicate(
                Root<Cliente> cliente,
                CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder
            ) {
                String[] busquedaListado = (null != filtro && null != filtro.getSearch() && !filtro.getSearch().trim().isEmpty())
                    ? filtro.getSearch().split(" ")
                    : new String[0];

                List<Predicate> organizador = new ArrayList<>();

                Expression<String> dni = cliente.get("dni").as(String.class);
                Expression<String> nombre = cliente.get("nombre").as(String.class);
                Expression<String> numeroCompras = cliente.get("numeroCompras").as(String.class);
                Expression<String> tier = cliente.get("tier").as(String.class);

                if (null != busquedaListado) {
                    for (int i = 0; i < busquedaListado.length; i++) {
                        String searchCriteria = busquedaListado[i].toLowerCase(Locale.getDefault());
                        List<Predicate> predicatesParametros = new ArrayList<>();

                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(dni), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(nombre), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(numeroCompras), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower(tier), "%" + searchCriteria + "%"));

                        organizador.add(criteriaBuilder.or(predicatesParametros.toArray(new Predicate[] {})));
                    }
                }

                query.distinct(true);

                return criteriaBuilder.and(organizador.toArray(new Predicate[] {}));
            }
        };
    }
}
