package com.firstProjectDemo.first_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {


    List<Order> findByStatus(String status);
}
