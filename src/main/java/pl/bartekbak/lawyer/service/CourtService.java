package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Court;

import java.util.List;

public interface CourtService {
    List<Court> findAllCourts();

    Court findCourtById(int id);

    void saveCourt(Court court);

    void deleteCourtById(int id);
}
