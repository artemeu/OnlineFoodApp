package com.bilgeadam.onlinefoodapp.dao;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final EntityManager entityManager;

    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getSession(){
        return  entityManager.unwrap(Session.class);
    }

    @Override
    public Optional<Customer> findById(Long customerId) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
        Root<Customer> root = cr.from(Customer.class);

        Query<Customer> query = getSession().createQuery(cr.select(root).where(cb.equal(root.get("customerId"), customerId)));
        List<Customer> results = query.getResultList();
        return !results.isEmpty() ? Optional.ofNullable(results.get(0)) : Optional.empty();
    }

    @Override
    public Optional<Customer> findByUsername(String username) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
        Root<Customer> root = cr.from(Customer.class);

        Query<Customer> query = getSession().createQuery(cr.select(root).where(cb.equal(root.get("username"), username)));
        List<Customer> results = query.getResultList();
        return !results.isEmpty() ? Optional.ofNullable(results.get(0)) : Optional.empty();

    }
}
