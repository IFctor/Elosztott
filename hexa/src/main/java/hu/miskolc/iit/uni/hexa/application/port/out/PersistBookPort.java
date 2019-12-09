package hu.miskolc.iit.uni.hexa.application.port.out;

import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import hu.miskolc.iit.uni.hexa.domain.Book;

public interface PersistBookPort {
    void saveBook(Book book) throws AuthorNotFoundException;
}