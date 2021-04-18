package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.PoaDTO;

import java.util.List;

public interface PoaService {
    List<PoaDTO> findAllPoa();

    PoaDTO findPoaById(int id);

    void savePoa(PoaDTO poa);

    void deletePoaById(int id);
}
