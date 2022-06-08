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
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "productNumber")
  private String productNumber;

  @Column(name = "modelNumber")
  private String modelNumber;

  @Column(name = "hoge")
  private String hoge;

  @Column(name = "yage")
  private String yage;

  @Column(name = "sage")
  private String sage;

  @Column(name = "age")
  private String age;

  @Column(name = "dage")
  private String dage;

  @Column(name = "sige")
  private String sige;

  @Column(name = "created")
  private String created;
}
