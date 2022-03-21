package pl.tkaczyk.nbpproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.tkaczyk.nbpproject.model.ComputerModel;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerModel, Long> {


    @Query(value = "select c from ComputerModel c where c.name like %?1% or c.date like %?1%")
    List<ComputerModel> findByKeyword(String keyword);
}
