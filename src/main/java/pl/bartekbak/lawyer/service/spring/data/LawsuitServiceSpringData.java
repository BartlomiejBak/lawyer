package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.LawsuitRepository;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.LawsuitService;

import java.util.List;
import java.util.Optional;

@Service
public class LawsuitServiceSpringData implements LawsuitService {
    LawsuitRepository lawsuitRepository;

    @Autowired
    public LawsuitServiceSpringData(LawsuitRepository lawsuitRepository) {
        this.lawsuitRepository = lawsuitRepository;
    }

    @Override
    public List<Lawsuit> findAllLawsuits() {
        return lawsuitRepository.findAllByOrderByDeadlineAsc();
    }

    @Override
    public Lawsuit findLawsuitById(int id) {
        Optional<Lawsuit> result = lawsuitRepository.findById(id);
        Lawsuit lawsuit = null;
        if (result.isPresent()){
            lawsuit = result.get();
        } else {
            throw new RuntimeException("Lawsuit id not found");
        }
        return lawsuit;
    }

    @Override
    public void saveLawsuit(Lawsuit lawsuit) {
        lawsuitRepository.save(lawsuit);
    }

    @Override
    public void deleteLawsuitById(int id) {
        lawsuitRepository.deleteById(id);
    }
}
