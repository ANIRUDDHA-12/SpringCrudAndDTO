package com.firstProjectDemo.first_api;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    OrderService(OrderRepository orderRepository, CustomerRepository customerRepository){
        this.orderRepository = orderRepository;
        this.customerRepository=customerRepository;}

    public Order placeOrder( OrderRequestDTO dto){

        Order order = new Order();
        Customer customer=customerRepository.findById(dto.customerId())
        .orElseThrow(()->new OrderNotFoundException("Customer not found with id "+dto.customerId()));

        order.setCustomer(customer);


//        order.setCustomer(dto.customerId());
        order.setProductName(dto.productName());
        order.setQuantity(dto.quantity());
        order.setPricePerItem(dto.pricePerItem());


        BigDecimal quantity = BigDecimal.valueOf(dto.quantity());
        BigDecimal pricePerItem=BigDecimal.valueOf(dto.pricePerItem());
        BigDecimal finalPrice= quantity.multiply(pricePerItem);
        order.setStatus("Pending");
        order.setTotalPrice(finalPrice.doubleValue());

        return orderRepository.save(order);
    }

    public void deleteById(int id){
       Order order = orderRepository.findById(id)
               .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));

       if("SHIPPED".equalsIgnoreCase(order.getStatus()) || "DELIVERED".equalsIgnoreCase(order.getStatus())){
           throw new InvalidOrderStateException("Cannot delete an order that has already been shipped or delivered.");
       }
       if("PENDING".equalsIgnoreCase(order.getStatus())){
           orderRepository.deleteById(id);
       }
       else{
           throw new InvalidOrderStateException("Order cannot be deleted while in " + order.getStatus() + " status.");
       }
    }

    public void updateOrder(int id,String newStatus){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));

         if("DELIVERED".equalsIgnoreCase(order.getStatus())){
            throw new InvalidOrderStateException("Order cannot be updated as its already delivered");
         }
             order.setStatus(newStatus);
         orderRepository.save(order);
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }


    public List<Order> getOrderByStatus(String status){
        return orderRepository.findByStatus(status);
    }



}
