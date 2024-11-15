package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entities.Book;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {

    private final SessionFactory sessionFactory;

    public BookDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "INSERT INTO PUBLIC.BOOKS (TITLE, AUTHOR, PUBLISHYEAR, GENRE, PAGECOUNT, DESCRIPTION) " +
                    "VALUES (:title, :author, :publishYear, :genre, :pageCount, :description)";
            Query query = session.createNativeQuery(sql);
            query.setParameter("title", book.getTitle());
            query.setParameter("author", book.getAuthor());
            query.setParameter("publishYear", book.getPublishYear());
            query.setParameter("genre", book.getGenre());
            query.setParameter("pageCount", book.getPageCount());
            query.setParameter("description", book.getDescription());
            query.executeUpdate();
            transaction.commit();
        }
    }

    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM PUBLIC.BOOKS";
            Query query = session.createNativeQuery(sql, Book.class);
            return query.getResultList();
        }
    }

    public Book getBookById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM PUBLIC.BOOKS WHERE ID = :id";
            Query query = session.createNativeQuery(sql, Book.class);
            query.setParameter("id", id);
            return (Book) query.getSingleResult();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM PUBLIC.BOOKS WHERE ID = :id";
            Query query = session.createNativeQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }

    public void update(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "UPDATE PUBLIC.BOOKS SET TITLE = :title, AUTHOR = :author, PUBLISHYEAR = :publishYear, " +
                    "GENRE = :genre, PAGECOUNT = :pageCount, DESCRIPTION = :description WHERE ID = :id";
            Query query = session.createNativeQuery(sql);
            query.setParameter("title", book.getTitle());
            query.setParameter("author", book.getAuthor());
            query.setParameter("publishYear", book.getPublishYear());
            query.setParameter("genre", book.getGenre());
            query.setParameter("pageCount", book.getPageCount());
            query.setParameter("description", book.getDescription());
            query.setParameter("id", book.getId());
            query.executeUpdate();
            transaction.commit();
        }
    }

    public List<Book> searchBooks(String title, String author, Integer publicationYear,
                                  String genre, Integer pageCount, String description) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM PUBLIC.BOOKS WHERE 1=1";
            if (title != null && !title.isEmpty()) {
                sql += " AND TITLE LIKE :title";
            }
            if (author != null && !author.isEmpty()) {
                sql += " AND AUTHOR LIKE :author";
            }
            if (publicationYear != null) {
                sql += " AND PUBLISHYEAR = :publicationYear";
            }
            if (genre != null && !genre.isEmpty()) {
                sql += " AND GENRE LIKE :genre";
            }
            if (pageCount != null) {
                sql += " AND PAGECOUNT = :pageCount";
            }
            if (description != null && !description.isEmpty()) {
                sql += " AND DESCRIPTION LIKE :description";
            }

            Query query = session.createNativeQuery(sql, Book.class);
            if (title != null && !title.isEmpty()) {
                query.setParameter("title", "%" + title + "%");
            }
            if (author != null && !author.isEmpty()) {
                query.setParameter("author", "%" + author + "%");
            }
            if (publicationYear != null) {
                query.setParameter("publicationYear", publicationYear);
            }
            if (genre != null && !genre.isEmpty()) {
                query.setParameter("genre", "%" + genre + "%");
            }
            if (pageCount != null) {
                query.setParameter("pageCount", pageCount);
            }
            if (description != null && !description.isEmpty()) {
                query.setParameter("description", "%" + description + "%");
            }

            return query.getResultList();
        }
    }
}
