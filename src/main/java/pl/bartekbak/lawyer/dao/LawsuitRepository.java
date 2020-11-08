package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.List;

public interface LawsuitRepository extends JpaRepository<Lawsuit, Integer> {

    public List<Lawsuit> findAllByOrderByDeadlineAsc();
}
