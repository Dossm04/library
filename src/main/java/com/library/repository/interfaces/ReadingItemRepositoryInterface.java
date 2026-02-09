package com.library.repository.interfaces;

import com.library.model.ReadingItem;
import java.util.List;

public interface ReadingItemRepositoryInterface {

    int create(ReadingItem item);

    List<ReadingItem> getAll();

    boolean updateStatus(int id, String status);

    boolean delete(int id);
}
