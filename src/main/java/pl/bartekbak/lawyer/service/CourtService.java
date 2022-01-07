package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.CourtDTO;

import java.util.List;
import java.util.UUID;

public interface CourtService {
    List<CourtDTO> findAllCourts();

    CourtDTO findCourtById(UUID id);

    void saveCourt(CourtDTO court);

    void deleteCourtById(UUID id);
}
