package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UniteOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
