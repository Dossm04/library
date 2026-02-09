package com.library.service;

import com.library.exception.InvalidInputException;
import com.library.exception.ResourceNotFoundException;
import com.library.model.ReadingItem;
import com.library.repository.interfaces.ReadingItemRepositoryInterface;
import com.library.service.interfaces.LibraryServiceInterface;

import java.util.List;

public class LibraryService implements LibraryServiceInterface {

    private final ReadingItemRepositoryInterface repo;

    public LibraryService(ReadingItemRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public void addItem(ReadingItem item) {
        if (item.getTitle() == null || item.getTitle().isBlank()) {
            throw new InvalidInputException("Title cannot be empty");
        }
        repo.create(item);
    }

    @Override
    public List<ReadingItem> getAllItemsSorted() {
        List<ReadingItem> items = repo.getAll();
        items.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle())); // lambda
        return items;
    }

    @Override
    public void updateItemStatus(int id, String status) {
        if (!repo.updateStatus(id, status)) {
            throw new ResourceNotFoundException("Item not found: " + id);
        }
    }

    @Override
    public void deleteItem(int id) {
        if (!repo.delete(id)) {
            throw new ResourceNotFoundException("Item not found: " + id);
        }
    }
}
