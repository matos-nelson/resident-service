package org.rent.circle.resident.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.resident.api.persistence.model.Resident;

@ApplicationScoped
public class ResidentRepository implements PanacheRepository<Resident> {

}
