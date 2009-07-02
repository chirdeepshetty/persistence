package com.tw.searchHistory.repository;

import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class Repository implements IRepository {
    private SessionFactory sessionFactory;

    public Repository() {
        this(HibernateUtil.getSessionFactory());
    }

    public Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(SearchHistory searchHistory){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(searchHistory);
        transaction.commit();
        session.close();
    }
}
