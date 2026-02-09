package com.library.service;

import com.library.model.ReadingItem;
import com.library.repository.BookRepository;
import com.library.service.interfaces.Validatable;

import java.util.List;

public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public int addBook(ReadingItem book) {
        if (book instanceof Validatable<?>) {
            ((Validatable<?>) book).validate();
        }
        return repository.create(book);
    }

    // READ
    public List<ReadingItem> getAllBooks() {
        return repository.getAll();
    }

    // UPDATE
    public boolean updateStatus(int id, String status) {
        Validatable.requireText(status, "Status cannot be empty");
        return repository.updateStatus(id, status);
    }

    // DELETE
    public boolean deleteBook(int id) {
        return repository.delete(id);
    }
}
