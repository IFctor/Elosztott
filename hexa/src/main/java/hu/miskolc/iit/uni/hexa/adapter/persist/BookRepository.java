package hu.miskolc.iit.uni.hexa.adapter.persist;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
}