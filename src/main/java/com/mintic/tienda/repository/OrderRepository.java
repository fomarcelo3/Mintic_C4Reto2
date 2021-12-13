package com.mintic.tienda.repository;

import com.mintic.tienda.model.Order;
import com.mintic.tienda.repository.crud.OrderCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    @Autowired
    private OrderCrudRepository orderCrudRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return (List<Order>) orderCrudRepository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudRepository.findById(id);
    }

    public Order create(Order order) {
        return orderCrudRepository.save(order);
    }

    public void update(Order order) {
        orderCrudRepository.save(order);
    }

    public void delete(Order order) {
        orderCrudRepository.delete(order);
    }

    public List<Order> getOrderByZone(String zone){
        return orderCrudRepository.findBySalesManZone(zone);
    }

    public Optional<Order> lastUserId(){
        return orderCrudRepository.findTopByOrderByIdDesc();
    }

    public List<Order> orderSalesManById(Integer id){
        Query query = new Query();
        Criteria criteria = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return  orders;
    }

    public  List<Order> ordersSalesManByStateAndId(String state, Integer id){
        Query query = new Query();
        Criteria criteria = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);

        query.addCriteria(criteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }




}
