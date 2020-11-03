package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    public List<Tag> findAllByOrderByNameAsc();
}
