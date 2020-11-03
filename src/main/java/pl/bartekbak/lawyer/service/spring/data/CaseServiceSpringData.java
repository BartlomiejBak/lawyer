package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.CaseRepository;
import pl.bartekbak.lawyer.entity.Case;
import pl.bartekbak.lawyer.service.CaseService;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceSpringData implements CaseService {
    CaseRepository caseRepository;

    @Autowired
    public CaseServiceSpringData(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<Case> findAllCases() {
        return caseRepository.findAllByOrderByDeadlineAsc();
    }

    @Override
    public Case findCaseById(int id) {
        Optional<Case> result = caseRepository.findById(id);
        Case aCase = null;
        if (result.isPresent()){
            aCase = result.get();
        } else {
            throw new RuntimeException("Case id not found");
        }
        return aCase;
    }

    @Override
    public void saveCase(Case aCase) {
        caseRepository.save(aCase);
    }

    @Override
    public void deleteCaseById(int id) {
        caseRepository.deleteById(id);
    }
}
