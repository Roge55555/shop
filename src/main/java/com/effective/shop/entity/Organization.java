package com.effective.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "name", unique = true)
    @Size(min = 3)
    @NotBlank
    private String name;

    @Column(name = "description")
    @Size(min = 10)
    private String description;

    @Column(name = "logo")
    private String logo;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @JsonIgnore
    @OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RegistrationProduct> registrationProductList;

    @JsonIgnore
    @OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RegistrationOrganization> registrationOrganizationList;

}
