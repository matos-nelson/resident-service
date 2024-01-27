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

    @Column(name = "tenant_id")
    private Long tenantId;
}
