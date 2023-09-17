package org.rent.circle.resident.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.resident.api.persistence.model.Resident;

@ApplicationScoped
public class ResidentRepository implements PanacheRepository<Resident> {

    public Resident findByEmail(String email) {
        Parameters queryParams = Parameters.with("email", email);
        return find("email = :email", queryParams)
            .singleResultOptional()
            .orElse(null);
    }
}
