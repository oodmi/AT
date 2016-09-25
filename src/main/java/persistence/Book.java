package persistence;

import org.springframework.stereotype.Component;

@Component
public class Book {

    private Integer ISN;
    private String author;
    private String name;

    public Book() {
    }

    public Book(Integer ISN, String author, String name) {
        this.ISN = ISN;
        this.author = author;
        this.name = name;
    }

    public Integer getISN() {
        return ISN;
    }

    public void setISN(Integer ISN) {
        this.ISN = ISN;
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
}
