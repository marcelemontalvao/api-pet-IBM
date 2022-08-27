package br.com.ibm.pet.repositorys;

import br.com.ibm.pet.entities.OrderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderServiceEntity, Long> {
}
