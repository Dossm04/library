package com.library.service.interfaces;

import com.library.model.ReadingItem;
import java.util.List;

public interface LibraryServiceInterface {

    void addItem(ReadingItem item);

    List<ReadingItem> getAllItemsSorted();

    void updateItemStatus(int id, String status);

    void deleteItem(int id);
}
