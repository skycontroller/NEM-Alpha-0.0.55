package org.nem.nis.j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.time.TimeInstant;
import org.nem.core.utils.e;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.j.zh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ui
implements zh {
    private final SessionFactory eo;

    @Autowired(required=1)
    public ui(SessionFactory sessionFactory) {
        this.eo = sessionFactory;
    }

    private Session getCurrentSession() {
        return this.eo.getCurrentSession();
    }

    @Transactional
    @Override
    public void a(Block block) {
        this.getCurrentSession().saveOrUpdate((Object)block);
    }

    @Transactional
    @Override
    public void b(Block block) {
        Query query = this.getCurrentSession().createQuery("UPDATE Block set nextBlockId = :nextBlockId where id = :blockId");
        query.setParameter("nextBlockId", (Object)block.getNextBlockId());
        query.setParameter("blockId", (Object)block.getId());
        query.executeUpdate();
    }

    @Transactional(readOnly=1)
    @Override
    public Long cV() {
        return (Long)this.getCurrentSession().createQuery("select count (*) from Block").uniqueResult();
    }

    @Transactional(readOnly=1)
    @Override
    public Block f(long l) {
        Criteria criteria = this.getCurrentSession().createCriteria((Class)Block.class).setFetchMode("blockTransfers", FetchMode.JOIN).add((Criterion)Restrictions.eq((String)"id", (Object)l));
        return (Block)this.a(criteria);
    }

    @Transactional(readOnly=1)
    @Override
    public Block s(BlockHeight blockHeight) {
        Criteria criteria = this.getCurrentSession().createCriteria((Class)Block.class).setFetchMode("blockTransfers", FetchMode.JOIN).add((Criterion)Restrictions.eq((String)"height", (Object)blockHeight.bw()));
        return (Block)this.a(criteria);
    }

    @Transactional(readOnly=1)
    @Override
    public Block f(Hash hash) {
        byte[] arrby = hash.o();
        long l = e.i(arrby);
        Criteria criteria = this.getCurrentSession().createCriteria((Class)Block.class).setFetchMode("blockTransfers", FetchMode.JOIN).add((Criterion)Restrictions.eq((String)"shortId", (Object)l));
        List<T> list = ui.b(criteria);
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            Block block;
            T T;
            if (!Arrays.equals(arrby, (block = (Block)(T = iterator.next())).getBlockHash().o())) continue;
            return block;
        }
        return null;
    }

    @Transactional(readOnly=1)
    @Override
    public HashChain c(BlockHeight blockHeight, int n) {
        List<T> list = this.a("blockHash", blockHeight, n);
        return HashChain.a(list);
    }

    @Transactional(readOnly=1)
    @Override
    public List<c> a(BlockHeight blockHeight, int n) {
        List<T> list = this.a("difficulty", blockHeight, n);
        ArrayList<c> arrayList = new ArrayList<c>(list.size());
        for (Long l : list) {
            arrayList.add(new c(l));
        }
        return arrayList;
    }

    @Transactional(readOnly=1)
    @Override
    public List<TimeInstant> b(BlockHeight blockHeight, int n) {
        List<T> list = this.a("timestamp", blockHeight, n);
        return list.stream().map(n -> new TimeInstant(n)).collect(Collectors.toList());
    }

    @Transactional(readOnly=1)
    @Override
    public Collection<Block> a(Account account, Integer n, int n2) {
        Criteria criteria = this.getCurrentSession().createCriteria((Class)Block.class).setFetchMode("forger", FetchMode.JOIN).setFetchMode("blockTransfers", FetchMode.SELECT).add((Criterion)Restrictions.le((String)"timestamp", (Object)n)).addOrder(Order.desc((String)"timestamp")).setMaxResults(n2).createCriteria("forger", "f").add((Criterion)Restrictions.eq((String)"f.printableKey", (Object)account.ai().ax()));
        return ui.b(criteria);
    }

    @Transactional
    @Override
    public void r(BlockHeight blockHeight) {
        Query query = this.getCurrentSession().createQuery("select tx.id from Block b join b.blockTransfers tx where b.height > :height").setParameter("height", (Object)blockHeight.bw());
        List<T> list = ui.b(query);
        Query query2 = this.getCurrentSession().createQuery("delete from Block a where a.height > :height").setParameter("height", (Object)blockHeight.bw());
        query2.executeUpdate();
        if (list.isEmpty()) return;
        Query query3 = this.getCurrentSession().createQuery("delete from Transfer t where t.id in (:ids)").setParameterList("ids", list);
        query3.executeUpdate();
    }

    private <T> T a(Criteria criteria) {
        List<T> list = ui.b(criteria);
        return list.size() > 0 ? list.get(0) : null;
    }

    private <T> List<T> a(String string, BlockHeight blockHeight, int n) {
        Criteria criteria = this.getCurrentSession().createCriteria((Class)Block.class).setMaxResults(n).add((Criterion)Restrictions.ge((String)"height", (Object)blockHeight.bw())).setProjection((Projection)Projections.property((String)string));
        return ui.b(criteria);
    }

    private static <T> List<T> b(Query query) {
        return query.list();
    }

    private static <T> List<T> b(Criteria criteria) {
        return criteria.list();
    }
}