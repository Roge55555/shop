package com.effective.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", unique = true)
    @NotBlank
    @Size(min = 4)
    private String login;

    @Column(name = "password")
    @NotBlank
    @Size(min = 5)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email", unique = true)
    @NotBlank
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "role")
    private AccessRole role;

    @JoinColumn(name = "balance")
    private Double balance;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Discount> discountList;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Organization> sessionList;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Notification> notificationCreatorList;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Notification> notificationReceiverList;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Rating> ratingList;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Purchase> purchaseList;

    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RegistrationProduct> registrationProductList;

    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RegistrationOrganization> registrationOrganizationList;

}
