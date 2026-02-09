package com.library.repository;

import com.library.model.EBook;
import com.library.model.PaperBook;
import com.library.model.ReadingItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public int create(ReadingItem b) {
        String sql = "INSERT INTO books(title, author, status, format, pages, file_size_mb) VALUES (?, ?, ?, ?, ?, ?) RETURNING book_id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getStatus());
            ps.setString(4, b.getFormat());

            if (b instanceof PaperBook pb) {
                ps.setInt(5, pb.getPages());
                ps.setNull(6, Types.NUMERIC);
            } else if (b instanceof EBook eb) {
                ps.setNull(5, Types.INTEGER);
                ps.setDouble(6, eb.getFileSizeMb());
            }

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Book create failed", e);
        }
    }

    public List<ReadingItem> getAll() {
        List<ReadingItem> list = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY book_id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String format = rs.getString("format");
                ReadingItem b = "PAPER".equals(format) ? new PaperBook() : new EBook();

                b.setId(rs.getInt("book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setStatus(rs.getString("status"));

                if (b instanceof PaperBook pb) pb.setPages(rs.getInt("pages"));
                if (b instanceof EBook eb) eb.setFileSizeMb(rs.getDouble("file_size_mb"));

                list.add(b);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Get books failed", e);
        }
        return list;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE books SET status = ? WHERE book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }
}