package com.project.shopapp.controllers;


import com.project.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {

    // them moi 1 order_detail
    @PostMapping
    public ResponseEntity createOrderDetails(@RequestBody @Valid OrderDetailDTO orderDetailDTO) {
        try {
            return ResponseEntity.ok("create OrderDetail here");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderDetailById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok("get OrderDetail with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{order-id}")
    public ResponseEntity getOrderDetails(@Valid @PathVariable("order-id") Long orderId) {
        try {
            return ResponseEntity.ok("get OrderDetails with orderId: " + orderId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrderDetail(@Valid @PathVariable("id") Long id, @RequestBody @Valid OrderDetailDTO newOrderDetailData) {
        try {
            return ResponseEntity.ok("update OrderDetail with orderDetailId: " + id + " newOrderDetailData: " + newOrderDetailData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok("delete OrderDetail with orderDetailId: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
