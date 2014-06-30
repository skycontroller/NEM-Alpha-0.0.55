package org.nem.nis.j;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.utils.e;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.j.cf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class te
implements cf {
    private final SessionFactory eo;

    @Autowired(required=1)
    public te(SessionFactory sessionFactory) {
        this.eo = sessionFactory;
    }

    private Session getCurrentSession() {
        return this.eo.getCurrentSession();
    }

    @Transactional
    @Override
    public void a(Transfer transfer) {
        this.getCurrentSession().saveOrUpdate((Object)transfer);
    }

    @Transactional(readOnly=1)
    @Override
    public Long cV() {
        return (Long)this.getCurrentSession().createQuery("select count (*) from Transfer").uniqueResult();
    }

    @Transactional(readOnly=1)
    @Override
    public Transfer l(byte[] arrby) {
        long l = e.i(arrby);
        Query query = this.getCurrentSession().createQuery("from Transfer a where a.shortId = :id").setParameter("id", (Object)l);
        List list = query.list();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object E;
            Transfer transfer;
            if (!Arrays.equals(arrby, (transfer = (Transfer)(E = iterator.next())).getTransferHash().o())) continue;
            return transfer;
        }
        return null;
    }

    @Transactional(readOnly=1)
    @Override
    public Collection<Object[]> b(Account account, Integer n, int n2) {
        Query query = this.getCurrentSession().createQuery("select t, t.block.height from Transfer t where t.timestamp <= :timestamp AND (t.recipient.printableKey = :pubkey OR t.sender.printableKey = :pubkey) order by t.timestamp desc").setParameter("timestamp", (Object)n).setParameter("pubkey", (Object)account.ai().ax()).setMaxResults(n2);
        return te.b(query);
    }

    @Override
    public void d(List<Transfer> list) {
        Session session = this.eo.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int n = 0;
            for (Transfer transfer : list) {
                session.saveOrUpdate((Object)transfer);
                if (++n != 20) continue;
                session.flush();
                session.clear();
                n = 0;
            }
            transaction.commit();
        }
        catch (RuntimeException var4_5) {
            if (transaction != null) {
                transaction.rollback();
            }
            var4_5.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    private static <T> List<T> b(Query query) {
        return query.list();
    }
}