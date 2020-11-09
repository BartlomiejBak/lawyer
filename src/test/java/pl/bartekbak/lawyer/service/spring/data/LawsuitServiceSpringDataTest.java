package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.LawsuitRepository;
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

    @Test
    void findAllLawsuitsTest() {
        //given
        List<Lawsuit> list = new ArrayList<>();
        Lawsuit lawsuitOne = new Lawsuit(1, "name1", "side1");
        Lawsuit lawsuitTwo = new Lawsuit(2, "name2", "side2");
        Lawsuit lawsuitThree = new Lawsuit(3, "name3", "side3");

        list.add(lawsuitOne);
        list.add(lawsuitTwo);
        list.add(lawsuitThree);

        //when
        when(repository.findAllByOrderByDeadlineAsc()).thenReturn(list);
        List<Lawsuit> result = service.findAllLawsuits();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByDeadlineAsc();
    }

    @Test
    void findLawsuitByIdTest() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(new Lawsuit(1, "name1", "side1")));
        Lawsuit result = service.findLawsuitById(1);
        //then
        assertEquals("name1", result.getName());
        assertEquals("side1", result.getCaseSide());
    }

    @Test
    void saveLawsuitTest() {
        //given
        Lawsuit lawsuit = new Lawsuit(1, "name1", "side1");
        //when
        service.saveLawsuit(lawsuit);
        service.saveLawsuit(new Lawsuit());
        //then
        verify(repository, times(1)).save(lawsuit);
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