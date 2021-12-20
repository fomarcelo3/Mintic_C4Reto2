package com.mintic.tienda.controller;

import com.mintic.tienda.model.Order;
import com.mintic.tienda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order gadget) {
        return orderService.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order order) {
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return orderService.delete(id);
    }

    //Metodo para ortener ordenes por zona
    @GetMapping("/zona/{zone}")
    public List<Order> orderByZone(@PathVariable("zone") String zone) {
        return orderService.getOrderByZone(zone);
    }

    //Metodo para ortener ordenes por vendedor
    @GetMapping("/salesman/{id}")
    public List<Order> orderSalesmanById(@PathVariable("id") Integer id) {
        return orderService.ordersSalesManById(id);
    }

    //Metodo para ortener ordenes por estado y asesor
    @GetMapping("/{state}/{id}")
    public List<Order> ordersSalesManByStateAndId(  @PathVariable("state") String state,@PathVariable("id") Integer id) {
        return orderService.ordersSalesManByStateAndId(state,id);
    }

    //Reto 4: Ordenes de un asesor x fecha
    @GetMapping("/date/{date}/{id}")
    public List<Order> ordersSalesManByDate(@PathVariable("date") String dateStr, @PathVariable("id") Integer id) {
        return orderService.ordersSalesManByDate(dateStr,id);
    }


}
