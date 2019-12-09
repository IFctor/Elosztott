package hu.miskolc.iit.uni.hexa.domain;

import lombok.Data;

@Data
public class Book {
    private final String title;
    private final Long authorId;
}
