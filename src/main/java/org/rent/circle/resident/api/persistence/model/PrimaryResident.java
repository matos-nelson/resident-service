package org.rent.circle.resident.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "primary_resident")
@Setter
@Getter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class PrimaryResident extends Resident {

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "user_id")
    private String userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resident_id", referencedColumnName = "id", nullable = false)
    private List<CoResident> coResidents;
}
