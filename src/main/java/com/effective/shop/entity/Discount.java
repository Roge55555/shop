package com.effective.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "amount")
    @NotNull
    @Positive
    private Long amount;

    @Column(name = "discount_from")
    private LocalDateTime discountFrom;

    @Column(name = "discount_to")
    private LocalDateTime discountTo;

    @JsonIgnore
    @OneToMany(mappedBy = "discount", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Product> productList;

}
