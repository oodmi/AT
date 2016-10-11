package controller.responses;

import persistence.Book;

public class BookResponse {
    private Long isn;
    private String author;
    private String name;
    private Long ownerId;
    private String owner;

    public BookResponse(Book book, String owner) {
        this.isn = book.isn;
        this.author = book.author;
        this.name = book.name;
        this.ownerId = book.ownerId;
        this.owner = owner;
    }

    public Long getIsn() {
        return isn;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getOwner() {
        return owner;
    }
}
