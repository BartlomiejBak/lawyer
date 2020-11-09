package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.CourtRepository;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Court;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourtServiceSpringDataTest {

    @InjectMocks
    CourtServiceSpringData service;

    @Mock
    CourtRepository repository;

    @Test
    void findAllCourtsTest() {
        //given
        List<Court> list = new ArrayList<>();
        Court courtOne = new Court(1,"name1", new Address(), "desc1");
        Court courtTwo = new Court(2,"name2", new Address(), "desc2");
        Court courtThree = new Court(3,"name3", new Address(), "desc3");

        list.add(courtOne);
        list.add(courtTwo);
        list.add(courtThree);

        //when
        when(repository.findAllByOrderByNameAsc()).thenReturn(list);
        List<Court> result = service.findAllCourts();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    void findCourtByIdTest() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(new Court(1,"name1", new Address(), "desc1")));
        Court result = service.findCourtById(1);
        //then
        assertEquals("name1", result.getName());
        assertEquals("desc1", result.getDescription());
    }

    @Test
    void saveCourtTest() {
        //given
        Court court = new Court(1,"name1", new Address(), "desc1");
        //when
        service.saveCourt(court);
        service.saveCourt(new Court());
        //then
        verify(repository, times(1)).save(court);
        verify(repository, times(2)).save(any());
    }

    @Test
    void deleteCourtByIdTest() {
        //given
        //when
        service.deleteCourtById(1);
        service.deleteCourtById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}