package book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookCollection {
    private List<Book> books;

    public BookCollection() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printByCategory(String category) {
        books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Book::getTitle)
                        .thenComparing(Book::getPrice))
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n) {
        if (this.books.size() < n) {
            return this.books;
        } else {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getPrice)
                            .thenComparing(Book::getTitle))
                    .limit(n)
                    .collect(Collectors.toList());
        }
    }
}
