package bts.sio.azurimmo.repository;

import bts.sio.azurimmo.model.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocataireRepository extends JpaRepository<Locataire, Long> {}