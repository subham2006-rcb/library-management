package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class LibrarySearch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        System.out.println("Enter Book Title to Search:");
        String s1 = sc.nextLine();

        String hql = "from Book";
        Query<Book> query = session.createQuery(hql, Book.class);

        List<Book> books = query.list();

        boolean found = false;

        for(Book b : books) {

            String s2 = b.getTitle();

            if(s1.equals(s2)) {   // equality check

                System.out.println("Book Found!");
                System.out.println("ID : " + b.getId());
                System.out.println("Title : " + b.getTitle());
                System.out.println("Author : " + b.getAuthor());

                found = true;
            }
        }

        if(!found) {
            System.out.println("Book Not Found");
        }

        session.getTransaction().commit();

        session.close();
        factory.close();
        sc.close();
    }
}