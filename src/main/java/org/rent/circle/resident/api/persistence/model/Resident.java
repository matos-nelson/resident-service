package org.rent.circle.resident.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.rent.circle.resident.api.persistence.BaseModel;

@Entity
@Table(name = "resident")
@Setter
@Getter
public class Resident extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resident_id", referencedColumnName = "id", nullable = false)
    private List<CoResident> coResidents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resident_id", referencedColumnName = "id", nullable = false)
    private List<Vehicle> vehicles;
}
