package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.CourtRepository;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.service.CourtService;

import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceSpringData implements CourtService {

    CourtRepository courtRepository;

    @Autowired
    public CourtServiceSpringData(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> findAllCourts() {
        return courtRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Court findCourtById(int id) {
        Optional<Court> result = courtRepository.findById(id);
        Court court = null;
        if (result.isPresent()){
            court = result.get();
        } else {
            throw new RuntimeException("Court id not found");
        }
        return court;
    }

    @Override
    public void saveCourt(Court court) {
        courtRepository.save(court);
    }

    @Override
    public void deleteCourtById(int id) {
        courtRepository.deleteById(id);
    }
}
