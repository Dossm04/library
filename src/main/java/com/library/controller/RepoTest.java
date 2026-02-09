package com.library.controller;

import com.library.model.PaperBook;
import com.library.model.ReadingItem;
import com.library.repository.BookRepository;

public class RepoTest {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();

        PaperBook b = new PaperBook();
        b.setTitle("Clean Code");
        b.setAuthor("Robert Martin");
        b.setStatus("PLANNED");
        b.setPages(450);

        int id = repo.create(b);
        System.out.println("Created book id=" + id);

        for (ReadingItem item : repo.getAll()) {
            System.out.println(item.info() + " | " + item.getAuthor());
        }
    }
}