package com.angularworkshop.app.repository.specification;

import com.angularworkshop.app.domain.Authority;
import com.angularworkshop.app.domain.User;
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

public interface UserSpecification {
    public static Specification<User> busquedaUsers(FilterHelper filtro) {
        final Logger log = LoggerFactory.getLogger(UserSpecification.class);
        return new Specification<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public javax.persistence.criteria.Predicate toPredicate(
                Root<User> user,
                CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder
            ) {
                String[] busquedaListado = (null != filtro && null != filtro.getSearch() && !filtro.getSearch().trim().isEmpty())
                    ? filtro.getSearch().split(" ")
                    : new String[0];

                List<Predicate> organizador = new ArrayList<>();
                Join<User, Authority> authority = user.join("authorities");

                Expression<String> login = user.get("login").as(String.class);
                Expression<String> name = user.get("firstName").as(String.class);
                Expression<String> apellido = user.get("lastName").as(String.class);
                Expression<String> mail = user.get("email").as(String.class);
                Expression<Boolean> activado = criteriaBuilder.isTrue(user.get("activated"));

                Expression<String> authorityString = authority.get("name").as(String.class);

                if (null != busquedaListado) {
                    for (int i = 0; i < busquedaListado.length; i++) {
                        String searchCriteria = busquedaListado[i].toLowerCase(Locale.getDefault());
                        List<Predicate> predicatesParametros = new ArrayList<>();

                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower((login)), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower((name)), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower((apellido)), "%" + searchCriteria + "%"));
                        predicatesParametros.add(criteriaBuilder.like(criteriaBuilder.lower((mail)), "%" + searchCriteria + "%"));
                        predicatesParametros.add(
                            criteriaBuilder.like(criteriaBuilder.lower((authorityString)), "%" + searchCriteria + "%")
                        );

                        organizador.add(criteriaBuilder.or(predicatesParametros.toArray(new Predicate[] {})));
                    }
                }

                query.distinct(true);

                return criteriaBuilder.and(criteriaBuilder.and(organizador.toArray(new Predicate[] {})), activado);
            }
        };
    }
}
