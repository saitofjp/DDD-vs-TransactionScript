package com.example.demo.controller;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.data.model.Order;
import com.example.demo.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createEmployee(@RequestBody OrderRequest request) {
        return orderService.order(request);
    }

    @GetMapping
    public List<Order> getAllEmployees() {
        return orderService.getOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getEmployeeById(@PathVariable("id") long employeeId) {
        return orderService.getEmployeeById(employeeId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}






















