package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.CourtRepository;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.service.CourtService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourtServiceSpringData implements CourtService {

    CourtRepository courtRepository;

    @Autowired
    public CourtServiceSpringData(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<CourtDTO> findAllCourts() {
        return courtRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Court::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourtDTO findCourtById(int id) {
        Optional<Court> result = courtRepository.findById(id);
        CourtDTO court;
        if (result.isPresent()){
            court = result.get().toDto();
        } else {
            throw new RuntimeException("Court id not found");
        }
        return court;
    }

    @Override
    public void saveCourt(CourtDTO court) {
        courtRepository.save(Court.fromDto(court));
    }

    @Override
    public void deleteCourtById(int id) {
        courtRepository.deleteById(id);
    }
}
