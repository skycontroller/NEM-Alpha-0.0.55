package org.nem.nis.j;

import java.sql.Connection;
import java.util.List;
import java.util.function.Consumer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.j.nn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ei
implements nn {
    private final SessionFactory eo;

    @Autowired(required=1)
    public ei(SessionFactory sessionFactory) {
        this.eo = sessionFactory;
    }

    private Session getCurrentSession() {
        return this.eo.getCurrentSession();
    }

    @Transactional(readOnly=1)
    @Override
    public Account a(Long l) {
        Query query = this.getCurrentSession().createQuery("from Account a where a.id = :id").setParameter("id", (Object)l);
        return ei.a(query);
    }

    @Transactional(readOnly=1)
    @Override
    public Account v(String string) {
        Query query = this.getCurrentSession().createQuery("from Account a where a.printableKey = :key").setParameter("key", (Object)string);
        return ei.a(query);
    }

    @Transactional
    @Override
    public void a(Account account) {
        this.getCurrentSession().saveOrUpdate((Object)account);
    }

    @Transactional(readOnly=1)
    @Override
    public Long cV() {
        return (Long)this.getCurrentSession().createQuery("select count (*) from Account").uniqueResult();
    }

    @Override
    public void d(List<Account> list) {
        Session session = this.eo.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            list.forEach(account -> {
                session.saveOrUpdate((Object)account);
            }
            );
            transaction.commit();
        }
        catch (RuntimeException var4_4) {
            if (transaction != null) {
                transaction.rollback();
            }
            var4_4.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    private static Account a(Query query) {
        List list = query.list();
        return list.size() > 0 ? (Account)list.get(0) : null;
    }
}