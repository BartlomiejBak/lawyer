package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.repository.CourtRepository;
import pl.bartekbak.lawyer.service.jooq.CourtServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourtServiceSpringDataTest {

    @InjectMocks
    CourtServiceJooq service;

    @Mock
    CourtRepository repository;

    private final UUID first = UUID.randomUUID();
    private final UUID second = UUID.randomUUID();
    private final UUID third = UUID.randomUUID();

    Court courtOne = Court.builder().courtId(first).name("name1").address(new Address()).description("desc1").build();

    @Test
    void findAllCourtsTest() {
        // given
        List<Court> list = new ArrayList<>();

        Court courtTwo = Court.builder().courtId(second).name("name2").address(new Address()).description("desc2").build();
        Court courtThree = Court.builder().courtId(third).name("name3").address(new Address()).description("desc3").build();

        list.add(courtOne);
        list.add(courtTwo);
        list.add(courtThree);

        // when
        when(repository.list()).thenReturn(list);
        List<CourtDTO> result = service.findAllCourts();

        // then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findCourtByIdTest() {
        // given
        // when
        when(repository.courtById(first))
                .thenReturn(Optional.of(courtOne));
        CourtDTO result = service.findCourtById(first);

        // then
        assertEquals("name1", result.getName());
        assertEquals("desc1", result.getDescription());
    }

    @Test
    void saveCourtTest() {
        // when
        service.saveCourt(courtOne.toDto());

        // then
        verify(repository, times(1)).add(any());
    }

    @Test
    void deleteCourtByIdTest() {
        // given
        // when
        service.deleteCourtById(first);
        service.deleteCourtById(second);

        // then
        verify(repository, times(1)).deleteById(first);
        verify(repository, times(2)).deleteById(any());
    }
}
