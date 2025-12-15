package bts.sio.azurimmo.repository;

import bts.sio.azurimmo.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {}