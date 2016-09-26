package persistence;

public class Book {

    private Integer isn;
    private String author;
    private String name;
    private String owner;

    public Book() {
    }

    public Book(Integer isn, String author, String name, String owner) {
        this.isn = isn;
        this.author = author;
        this.name = name;
        this.owner = owner;
    }

    public Integer getIsn() {
        return isn;
    }

    public void setIsn(Integer isn) {
        this.isn = isn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
