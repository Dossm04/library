package com.library.repository;

import com.library.model.ReadingItem;
import com.library.repository.interfaces.ReadingItemRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class ReadingItemRepository implements ReadingItemRepositoryInterface {

    private final List<ReadingItem> storage = new ArrayList<>();

    @Override
    public int create(ReadingItem item) {
        storage.add(item);
        return 1;
    }

    @Override
    public List<ReadingItem> getAll() {
        return storage;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}