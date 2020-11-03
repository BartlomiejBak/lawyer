package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Court;

import java.util.List;

public interface CourtService {
    public List<Court> findAllCourts();

    public Court findCourtById(int id);

    public void saveCourt(Court court);

    public void deleteCourtById(int id);
}
