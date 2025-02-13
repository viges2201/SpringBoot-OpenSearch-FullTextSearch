package org.opensearch.data.example.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Document(indexName = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private int publicationYear;

    private String authorName;

    private String isbn;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
