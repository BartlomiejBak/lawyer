package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.repository.CourtRepository;
import pl.bartekbak.lawyer.service.CourtService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourtServiceJooq implements CourtService {

    CourtRepository courtRepository;

    @Autowired
    public CourtServiceJooq(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<CourtDTO> findAllCourts() {
        return courtRepository.list()
                .stream()
                .map(Court::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourtDTO findCourtById(UUID id) {
        Optional<Court> result = courtRepository.courtById(id);
        CourtDTO court;
        court = result.map(Court::toDto).orElse(null);
        return court;
    }

    @Override
    public void saveCourt(CourtDTO court) {
        courtRepository.add(Court.fromDto(court));
    }

    @Override
    public void deleteCourtById(UUID id) {
        courtRepository.deleteById(id);
    }
}
