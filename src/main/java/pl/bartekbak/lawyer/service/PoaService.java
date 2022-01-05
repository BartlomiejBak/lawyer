package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.PoaDTO;

import java.util.List;
import java.util.UUID;

public interface PoaService {
    List<PoaDTO> findAllPoa();

    PoaDTO findPoaById(UUID id);

    void savePoa(PoaDTO poa);

    void deletePoaById(UUID id);
}
