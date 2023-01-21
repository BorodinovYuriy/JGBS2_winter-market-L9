package ru.gb.wintermarket.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.wintermarket.core.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
