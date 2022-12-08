package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name ="orders")
public class Order {


    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    private LocalDateTime orderDate; //order time

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // order status

    public void setMember(Member member){//연관관계 편의메서드
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){ //연관관계 편의메서드
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){//연관관계 편의메서드
        this.delivery = delivery;
        delivery.setOrder(this);
    }










}
