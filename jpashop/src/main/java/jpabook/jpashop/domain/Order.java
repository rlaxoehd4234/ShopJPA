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
    //== 생성 메서드 ==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order(); // make a new order
        order.setMember(member); // It's member's
        order.setDelivery(delivery); // delivery status
        for(OrderItem orderItem : orderItems){  // orderItems add
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }


    //==비즈니스 로직 ==//
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }


    //==조회 로직==//
   //전체주문 가격
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItems : orderItems){
            totalPrice += orderItems.getTotalPrice();
        }
        return totalPrice;
    }







}
