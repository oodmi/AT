package persistence;


public class Book {

    public Long isn;
    public String author;
    public String name;
    public Long ownerId;

    public Book() {
    }

    public Book(Long isn, String author, String name, Long ownerId) {
        this.isn = isn;
        this.author = author;
        this.name = name;
        this.ownerId = ownerId;
    }

}
