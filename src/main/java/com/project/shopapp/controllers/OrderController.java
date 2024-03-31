package com.project.shopapp.controllers;


import com.project.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {

    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody @Valid OrderDTO orderDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("create Order successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity getOrdersByUserId(@Valid @PathVariable("user_id") Long userId) {
        try {

            return ResponseEntity.ok("Lấy ra danh sách order từ user_id");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());


        }
    }

    @PutMapping("/{id}")
    // PUT http://localhost:8088/api/v1/orders/2
    public ResponseEntity updateOrder(@Valid @PathVariable long id, @Valid @RequestBody OrderDTO orderDTO) {
        try {
            return ResponseEntity.ok("Cập nhật thông tin 1 order");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@Valid @PathVariable Long id) {
        // xoá mềm => cập nhật trường active = false
        try {
            return ResponseEntity.ok("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
