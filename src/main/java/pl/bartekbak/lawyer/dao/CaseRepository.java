package pl.bartekbak.lawyer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bartekbak.lawyer.entity.Case;

import java.util.List;

public interface CaseRepository extends CrudRepository<Case, Integer> {

    public List<Case> findAllByOrderByDeadlineAsc();
}
