package com.system.bike_rental_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bike")
public class Bike {
    @Id
    @SequenceGenerator(name = "cms_user_seq_gen", sequenceName = "cms_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "cms_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String bikeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_categoryId"))
    private Category category;

    @Column
    private String brandName;

    @Column
    private Integer topSpeed;

    @Column
    private String mileage;

    @Column
    private String power;

    @Column
    private String transmission;

    @Column
    private String tankCapacity;

    @Column
    private String maxTorque;

    @Column
    private Integer priceDay;

    @Column
    private Integer availableNo;

    @Column
    private String bikeImage;

    @Transient
    private String bikeImageBase64;

    @Column
    private Integer rentedNumber;
}
