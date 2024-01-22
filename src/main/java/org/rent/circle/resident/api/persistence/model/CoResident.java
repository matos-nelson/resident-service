package org.rent.circle.resident.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "co_resident")
@Setter
@Getter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CoResident extends Resident {

    @Column(name = "resident_id", insertable = false, updatable = false, nullable = false)
    private Long residentId;
}
