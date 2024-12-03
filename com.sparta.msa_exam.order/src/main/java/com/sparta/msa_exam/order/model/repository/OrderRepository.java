package com.sparta.msa_exam.order.model.repository;

import com.sparta.msa_exam.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
