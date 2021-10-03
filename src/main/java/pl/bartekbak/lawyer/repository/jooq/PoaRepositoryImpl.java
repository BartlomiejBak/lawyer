package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.repository.PoaRepository;

import java.util.List;
import java.util.Optional;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbPoa.DB_POA;

@Repository
public class PoaRepositoryImpl extends DatabaseContext implements PoaRepository {

    public PoaRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Poa> list() {
        return dslContext().selectFrom(DB_POA)
                .fetch()
                .map(Poa::fromDbRecord);
    }

    @Override
    public Optional<Poa> poaById(int id) {
        return dslContext().selectFrom(DB_POA)
                .where(DB_POA.POA_ID.eq(id))
                .fetchOptional()
                .map(Poa::fromDbRecord);
    }

    @Override
    public void add(Poa poa) {
        dslContext().insertInto(DB_POA)
                .set(DB_POA.TYPE, poa.getType())
                .set(DB_POA.PAYMENT, poa.getPayment())
                .set(DB_POA.KPC, poa.isKpc())
                .set(DB_POA.TERMINATION, poa.isTermination())
                .set(DB_POA.START_DATE, poa.getStartDate())
                .set(DB_POA.END_DATE, poa.getEndDate())
                .set(DB_POA.NOTIFICATION_DUTY, poa.isTerminationNotificationDuty())
                .set(DB_POA.DUTY_COMPLETED, poa.isTerminationNotificationDutyCompleted())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public void update(Poa poa) {
        dslContext().update(DB_POA)
                .set(DB_POA.TYPE, poa.getType())
                .set(DB_POA.PAYMENT, poa.getPayment())
                .set(DB_POA.KPC, poa.isKpc())
                .set(DB_POA.TERMINATION, poa.isTermination())
                .set(DB_POA.START_DATE, poa.getStartDate())
                .set(DB_POA.END_DATE, poa.getEndDate())
                .set(DB_POA.NOTIFICATION_DUTY, poa.isTerminationNotificationDuty())
                .set(DB_POA.DUTY_COMPLETED, poa.isTerminationNotificationDutyCompleted())
                .where(DB_POA.POA_ID.eq(poa.getPoaId()))
                .execute();
    }

    @Override
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_POA)
                .where(DB_POA.POA_ID.eq(id))
                .execute();
    }
}
