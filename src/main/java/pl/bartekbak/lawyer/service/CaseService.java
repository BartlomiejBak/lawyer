package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Case;

import java.util.List;

public interface CaseService {
    public List<Case> findAllCases();
}
