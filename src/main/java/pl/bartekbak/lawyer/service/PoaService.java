package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Poa;

import java.util.List;

public interface PoaService {
    List<Poa> findAllPoa();

    Poa findPoaById(int id);

    void savePoa(Poa poa);

    void deletePoaById(int id);
}
