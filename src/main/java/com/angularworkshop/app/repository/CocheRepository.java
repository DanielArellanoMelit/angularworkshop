package com.angularworkshop.app.repository;

import com.angularworkshop.app.domain.Coche;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Coche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>, JpaSpecificationExecutor<Coche> {
    List<Coche> findAllByExposicionTrue();

    List<Coche> findAllByExposicionFalse();
}
