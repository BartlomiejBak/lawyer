package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.repository.CourtRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static pl.bartekbak.lawyer.generate.jooq.Tables.*;

@Repository
public class CourtRepositoryImpl extends DatabaseContext implements CourtRepository {

    public CourtRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Court> list() {
        return dslContext().select(
                        DB_COURT.asterisk(),
                        field(
                                select(jsonObject(DB_ADDRESS.fields()))
                                        .from(DB_ADDRESS)
                                        .join(DB_COURT_ADDRESS)
                                        .on(DB_ADDRESS.ADDRESS_ID.eq(DB_COURT_ADDRESS.ADDRESS))
                                        .where(DB_COURT_ADDRESS.COURT.eq(DB_COURT.COURT_ID))
                        )
                                .as("address")
                )
                .from(DB_COURT)
                .fetchInto(Court.class);
    }

    @Override
    public Optional<Court> courtById(UUID id) {
        return Optional.ofNullable(dslContext().select(
                        DB_COURT.asterisk(),
                        field(
                                select(jsonObject(DB_ADDRESS.fields()))
                                        .from(DB_ADDRESS)
                                        .join(DB_COURT_ADDRESS)
                                        .on(DB_ADDRESS.ADDRESS_ID.eq(DB_COURT_ADDRESS.ADDRESS))
                                        .where(DB_COURT_ADDRESS.COURT.eq(DB_COURT.COURT_ID))
                        )
                                .as("address")
                )
                .from(DB_COURT)
                        .where(DB_COURT.COURT_ID.eq(id))
                .fetchOneInto(Court.class));
    }

    @Override
    @Transactional
    public void add(Court court) {
        dslContext().insertInto(DB_COURT)
                .set(DB_COURT.COURT_ID, court.getCourtId())
                .set(DB_COURT.NAME, court.getName())
                .set(DB_COURT.DESCRIPTION, court.getDescription())
                .set(DB_COURT.PHONE, court.getPhone())
                .onDuplicateKeyIgnore()
                .execute();
    }

    // todo add/remove address to contact

    @Override
    @Transactional
    public void update(Court court) {
        dslContext().update(DB_COURT)
                .set(DB_COURT.NAME, court.getName())
                .set(DB_COURT.DESCRIPTION, court.getDescription())
                .set(DB_COURT.PHONE, court.getPhone())
                .where(DB_COURT.COURT_ID.eq(court.getCourtId()))
                .andNotExists(dslContext().selectFrom(DB_COURT)
                        .where(DB_COURT.NAME.eq(court.getName())))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        dslContext().deleteFrom(DB_COURT)
                .where(DB_COURT.COURT_ID.eq(id))
                .execute();
    }
}
