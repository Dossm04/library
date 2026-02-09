package com.library.model;

import com.library.model.Category;

public abstract class ReadingItem extends BaseEntity implements Readable {
    protected String author;
    protected String status;
    protected Category category;

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }



    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public abstract String getItemType();
    public abstract int estimateReadingTimeMinutes();

    public String shortInfo() {
        return getId() + " | " + getTitle() + " | " + author + " | " + status + " | " + getItemType();
    }
}
