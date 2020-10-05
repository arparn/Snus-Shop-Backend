package ee.taltech.webpage.repository;


import ee.taltech.webpage.model.ItemCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCountRepository extends JpaRepository<ItemCount, Long> {
}
