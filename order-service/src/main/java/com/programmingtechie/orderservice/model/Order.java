package com.programmingtechie.orderservice.model;

import lombok.Data;

//import javax.persistence.*;
//To remove Not a managed type as javax is new named as jakarta
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;
}
