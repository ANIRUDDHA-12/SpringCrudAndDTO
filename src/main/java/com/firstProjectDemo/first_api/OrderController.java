package com.firstProjectDemo.first_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderRequestDTO request){
        Order order= orderService.placeOrder(request);
        return new  ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
            orderService.deleteById(id);
          return  ResponseEntity.ok("Order deleted successfully");

    }

    @PutMapping("/{id}/status/{newStatus}")
    public ResponseEntity<String> updateOrder(@PathVariable int id,@PathVariable String newStatus){
            orderService.updateOrder(id,newStatus);
            return ResponseEntity.ok("Order status updated successfully to "+newStatus);

    }

    @GetMapping("/customer")
    public ResponseEntity<List<Order>> findOrdersByEmail(@RequestParam String email){
       List<Order> orders= orderService.getOrdersByEmail(email);
       return ResponseEntity.ok(orders);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Order>> findByStatus(@RequestParam String status){
        List<Order> orders = orderService.getOrderByStatus(status);
        return ResponseEntity.ok(orders);
    }


}
