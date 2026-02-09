package com.library.controller;

import com.library.model.EBook;
import com.library.model.PaperBook;
import com.library.model.ReadingItem;
import com.library.repository.BookRepository;
import com.library.repository.ReadingItemRepository;
import com.library.repository.interfaces.ReadingItemRepositoryInterface;
import com.library.service.BookService;
import com.library.service.LibraryService;
import com.library.service.interfaces.LibraryServiceInterface;
import com.library.model.Category;
import com.library.model.PaperBook;

import java.util.Scanner;
import com.library.model.*;
import com.library.utils.ReflectionUtils;

public class Main {
    public static void main(String[] args) {
        ReadingItemRepositoryInterface repo = new ReadingItemRepository();
        LibraryServiceInterface service = new LibraryService(repo);

        Category fantasy = new Category(1, "Fantasy");

        ReadingItem book = new PaperBook(1, "Harry Potter", "J.K. Rowling", "planned", 350);
        ReflectionUtils.printFields(book);
        service.addItem(book);

        book.setCategory(fantasy);


        System.out.println(book.shortInfo());
        System.out.println("Category: " + book.getCategory().getName());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LibraryLite ===");
            System.out.println("1) Add Paper Book");
            System.out.println("2) Add EBook");
            System.out.println("3) List Books");
            System.out.println("4) Update Status");
            System.out.println("5) Delete Book");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String cmd = sc.nextLine().trim();

            try {
                switch (cmd) {
                    case "1" -> {
                        PaperBook b = new PaperBook();
                        System.out.print("Title: "); b.setTitle(sc.nextLine());
                        System.out.print("Author: "); b.setAuthor(sc.nextLine());
                        System.out.print("Status (PLANNED/READING/FINISHED): "); b.setStatus(sc.nextLine().trim().toUpperCase());
                        System.out.print("Pages: "); b.setPages(Integer.parseInt(sc.nextLine()));

                        b.validate();
                        int id = repo.create(b);
                        System.out.println("✅ Created paper book id=" + id);

                        // Polymorphism demo
                        ReadingItem asParent = b;
                        System.out.println("Polymorphism: " + asParent.info() + " format=" + asParent.getFormat());
                    }
                    case "2" -> {
                        EBook b = new EBook();
                        System.out.print("Title: "); b.setTitle(sc.nextLine());
                        System.out.print("Author: "); b.setAuthor(sc.nextLine());
                        System.out.print("Status (PLANNED/READING/FINISHED): "); b.setStatus(sc.nextLine().trim().toUpperCase());
                        System.out.print("File size (MB): "); b.setFileSizeMb(Double.parseDouble(sc.nextLine()));

                        b.validate();
                        int id = repo.create(b);
                        System.out.println("✅ Created ebook id=" + id);

                        ReadingItem asParent = b;
                        System.out.println("Polymorphism: " + asParent.info() + " format=" + asParent.getFormat());
                    }
                    case "3" -> {
                        System.out.println("\n--- BOOKS ---");
                        for (ReadingItem b : repo.getAll()) {
                            System.out.println(b.info() + " | " + b.getAuthor() + " | " + b.getStatus() + " | " + b.getFormat());
                        }
                    }
                    case "4" -> {
                        System.out.print("Book id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("New status (PLANNED/READING/FINISHED): ");
                        String st = sc.nextLine().trim().toUpperCase();

                        boolean ok = repo.updateStatus(id, st);
                        System.out.println(ok ? "✅ Updated!" : "❌ Book not found");
                    }
                    case "5" -> {
                        System.out.print("Book id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean ok = repo.delete(id);
                        System.out.println(ok ? "✅ Deleted!" : "❌ Book not found");
                    }
                    case "0" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Unknown command");
                }
            } catch (Exception e) {
                System.out.println("❗ Error: " + e.getMessage());
            }
        }
    }
}
