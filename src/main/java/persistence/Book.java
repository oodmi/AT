package persistence;


public class Book {

    public Integer isn;
    public String author;
    public String name;
    public Long ownerId;

    public Book() {
    }

    public Book(Integer isn, String author, String name, Long ownerId) {
        this.isn = isn;
        this.author = author;
        this.name = name;
        this.ownerId = ownerId;
    }

}
