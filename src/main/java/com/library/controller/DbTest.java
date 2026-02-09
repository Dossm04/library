package com.library.controller;

import com.library.repository.DatabaseConnection;

import java.sql.Connection;

public class DbTest {
    public static void main(String[] args) {
        try (Connection c = DatabaseConnection.getConnection()) {  // <-- getConnection
            System.out.println("✅ Connected: " + c.getMetaData().getURL());
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
