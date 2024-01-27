package org.rent.circle.resident.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.resident.api.persistence.model.Resident;

@ApplicationScoped
public class ResidentRepository implements PanacheRepository<Resident> {

    public Resident findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }

    public Resident findByIdAndManagerId(Long id, String managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }
}
