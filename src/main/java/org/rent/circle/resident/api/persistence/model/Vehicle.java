package org.rent.circle.resident.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.resident.api.persistence.BaseModel;

@Entity
@Table(name = "resident_vehicle")
@Setter
@Getter
@ToString
public class Vehicle extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year_made")
    private Integer year;

    @Column(name = "color")
    private String color;

    @Column(name = "licence_num")
    private String licenceNumber;

    @Column(name = "resident_id", insertable = false, updatable = false, nullable = false)
    private Long resident_id;
}