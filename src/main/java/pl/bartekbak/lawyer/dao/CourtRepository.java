package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Court;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Integer> {

    public List<Court> findAllByOrderByNameAsc();
}
