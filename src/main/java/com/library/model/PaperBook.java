package com.library.model;

public class PaperBook extends ReadingItem {
    private int pages;

    public PaperBook() {}

    public PaperBook(int id, String title, String author, String status, int pages) {
        setId(id);
        setTitle(title);
        this.author = author;
        this.status = status;
        this.pages = pages;
    }

    @Override
    public String getItemType() {
        return "PaperBook";
    }

    @Override
    public int estimateReadingTimeMinutes() {
        return pages * 2;
    }


    @Override
    public String getEntityType() {
        return "";
    }

    @Override
    public void validate() {
        if (getTitle() == null || getTitle().isBlank())
            throw new RuntimeException("Title required");

        if (author == null || author.isBlank())
            throw new RuntimeException("Author required");

        if (pages <= 0)
            throw new RuntimeException("Pages must be > 0");
    }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }


    @Override
    public String getFormat() {
        return "";
    }
}