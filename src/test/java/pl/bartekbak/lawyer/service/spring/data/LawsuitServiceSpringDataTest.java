package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.LawsuitRepository;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class LawsuitServiceSpringDataTest {

    @InjectMocks
    LawsuitServiceSpringData service;

    @Mock
    LawsuitRepository repository;

    Lawsuit lawsuitOne = Lawsuit.builder()
            .lawsuitId(1)
            .name("name1")
            .caseSide("side1")
            .build();

    @Test
    void findAllLawsuitsTest() {
        //given
        List<Lawsuit> list = new ArrayList<>();

        Lawsuit lawsuitTwo = Lawsuit.builder()
                .lawsuitId(2)
                .name("name2")
                .caseSide("side2")
                .build();
        Lawsuit lawsuitThree = Lawsuit.builder()
                .lawsuitId(3)
                .name("name3")
                .caseSide("side3")
                .build();

        list.add(lawsuitOne);
        list.add(lawsuitTwo);
        list.add(lawsuitThree);

        //when
        when(repository.findAllByOrderByDeadlineAsc()).thenReturn(list);
        List<LawsuitDTO> result = service.findAllLawsuits();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByDeadlineAsc();
    }

    @Test
    void findLawsuitByIdTest() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(lawsuitOne));
        LawsuitDTO result = service.findLawsuitById(1);
        //then
        assertEquals("name1", result.getName());
        assertEquals("side1", result.getCaseSide());
    }

    @Test
    void saveLawsuitTest() {
        //given
        //when
        service.saveLawsuit(lawsuitOne.toDto());
        service.saveLawsuit(new LawsuitDTO());
        //then
        verify(repository, times(2)).save(any());
    }

    @Test
    void deleteLawsuitByIdTest() {
        //given
        //when
        service.deleteLawsuitById(1);
        service.deleteLawsuitById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}
