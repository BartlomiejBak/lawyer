package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.CaseRepository;
import pl.bartekbak.lawyer.entity.Case;
import pl.bartekbak.lawyer.service.CaseService;

import java.util.List;
@Service
public class CaseServiceSpringData implements CaseService {
    CaseRepository caseRepository;

    @Autowired
    public CaseServiceSpringData(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<Case> findAllCases() {
        return null;
    }
}
