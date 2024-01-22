package org.rent.circle.resident.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;

@ApplicationScoped
public class ResidentRepository implements PanacheRepository<PrimaryResident> {

    public PrimaryResident findByEmail(String email) {
        Parameters queryParams = Parameters.with("email", email);
        return find("email = :email", queryParams)
            .singleResultOptional()
            .orElse(null);
    }

    public PrimaryResident findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }

    public PrimaryResident findByIdAndManagerId(Long id, String managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }
}
