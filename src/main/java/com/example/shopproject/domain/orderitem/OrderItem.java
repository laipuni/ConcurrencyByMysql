package com.example.shopproject.domain.orderitem;

import com.example.shopproject.domain.item.Item;
import com.example.shopproject.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "orderItem_count",nullable = false)
    private int count;

    @Column(nullable = false)
    private int itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    private OrderItem(final int count, final int itemPrice,final Order order,final Item item) {
        this.count = count;
        this.itemPrice = itemPrice;
        this.order = order;
        this.item = item;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public static OrderItem create(final Item item,final int count){
        item.reductQuantity(count);

        return OrderItem.builder()
                .item(item)
                .count(count)
                .itemPrice(item.getPrice())
                .build();
    }
}
