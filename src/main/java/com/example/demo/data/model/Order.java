package com.example.demo.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "pproductId")
    private long productId;

    @Column(name = "pitemNumber")
    private String itemNumber;

    @Column(name = "count")
    private int count;

    @Column(name = "kari")
    private boolean kari;

    @Column(name = "kariStatus")
    private String kariStatus;

    @Column(name = "phoge")
    private String phoge;

    @Column(name = "pyage")
    private String pyage;

    @Column(name = "psage")
    private String psage;

    @Column(name = "page")
    private String page;

    @Column(name = "pdage")
    private String pdage;

    @Column(name = "psige")
    private String psige;


}
