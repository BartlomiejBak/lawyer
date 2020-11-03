package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Case;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Integer> {

    public List<Case> findAllByOrderByDeadlineAsc();
}
