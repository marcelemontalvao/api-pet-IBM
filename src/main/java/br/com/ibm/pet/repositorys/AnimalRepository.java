package br.com.ibm.pet.repositorys;

import br.com.ibm.pet.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}
