package hu.miskolc.iit.uni.hexa.application.port.out;

import hu.miskolc.iit.uni.hexa.domain.Author;

public interface PersistAuthorPort {
    void saveAuthor(Author author);
}
