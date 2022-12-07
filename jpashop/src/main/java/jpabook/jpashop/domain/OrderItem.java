package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 모든 로딩은 지연 로딩으로 구현해야 한다.
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)     // 모든 로딩은 지연 로딩으로 구현해야 한다.
    @JoinColumn(name = "order_id")
    private Order order;
    
    private int orderPrice;

    private int count;




}
