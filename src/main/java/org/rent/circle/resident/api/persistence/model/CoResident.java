package org.rent.circle.resident.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.rent.circle.resident.api.persistence.BaseModel;

@Entity
@Table(name = "co_resident")
@Setter
@Getter
public class CoResident extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "resident_id", insertable = false, updatable = false, nullable = false)
    private Long residentId;
}
