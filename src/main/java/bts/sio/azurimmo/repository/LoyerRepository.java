package bts.sio.azurimmo.repository;

import bts.sio.azurimmo.model.Loyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyerRepository extends JpaRepository<Loyer, Long> {}