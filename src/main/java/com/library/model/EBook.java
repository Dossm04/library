package com.library.model;

public class EBook extends ReadingItem {
    private double fileSizeMb;

    @Override
    public String getItemType() {
        return "EBook";
    }
    @Override
    public int estimateReadingTimeMinutes() {
        return (int)(fileSizeMb * 5);
    }

    @Override public String getEntityType() { return "EBOOK"; }
    @Override public String getFormat() { return "EBOOK"; }

    @Override
    public void validate() {
        if (title == null || title.isBlank()) throw new RuntimeException("Title required");
        if (author == null || author.isBlank()) throw new RuntimeException("Author required");
        if (fileSizeMb <= 0) throw new RuntimeException("File size must be > 0");
    }

    public double getFileSizeMb() { return fileSizeMb; }
    public void setFileSizeMb(double fileSizeMb) { this.fileSizeMb = fileSizeMb; }
}
