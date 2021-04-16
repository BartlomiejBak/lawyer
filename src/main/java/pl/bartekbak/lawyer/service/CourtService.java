package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.CourtDTO;

import java.util.List;

public interface CourtService {
    List<CourtDTO> findAllCourts();

    CourtDTO findCourtById(int id);

    void saveCourt(CourtDTO court);

    void deleteCourtById(int id);
}
