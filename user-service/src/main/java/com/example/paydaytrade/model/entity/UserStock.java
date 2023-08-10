package com.example.paydaytrade.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String symbol;
    String name;
    String currency;
    String exchange;
    String mic_code;
    String country;
    String type;
    int buyPrice;
    int sellPrice;
    @Builder.Default
    boolean buyStatus = false;
    @Builder.Default
    boolean sellStatus = false;
    @Builder.Default
    boolean sellRequest = false;

    @ManyToOne
    User user;
}
