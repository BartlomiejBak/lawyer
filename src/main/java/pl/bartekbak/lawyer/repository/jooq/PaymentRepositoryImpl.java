package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbPayment.DB_PAYMENT;

@Repository
public class PaymentRepositoryImpl extends DatabaseContext implements PaymentRepository {

    public PaymentRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Payment> list() {
        return dslContext().selectFrom(DB_PAYMENT)
                .fetch()
                .map(Payment::fromDbRecord);
    }

    @Override
    public Optional<Payment> paymentById(int id) {
        return dslContext().selectFrom(DB_PAYMENT)
                .where(DB_PAYMENT.PAYMENT_ID.eq(id))
                .fetchOptional()
                .map(Payment::fromDbRecord);
    }

    @Override
    @Transactional
    public void add(Payment payment) {
        dslContext().insertInto(DB_PAYMENT)
                .set(DB_PAYMENT.PAYMENT_ID, payment.getPaymentId())
                .set(DB_PAYMENT.PAYMENT_DATE, payment.getPaymentDate())
                .set(DB_PAYMENT.PAYMENT_VALUE, payment.getPaymentValue())
                .set(DB_PAYMENT.PAID, payment.isPaid())
                .set(DB_PAYMENT.COMMENT, payment.getComment())
                .set(DB_PAYMENT.INCOMING, payment.isIncoming())
                .set(DB_PAYMENT.PAID_DATE, payment.getPaymentDate())
                .set(DB_PAYMENT.US, payment.isUs())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Payment payment) {
        dslContext().update(DB_PAYMENT)
                .set(DB_PAYMENT.PAYMENT_DATE, payment.getPaymentDate())
                .set(DB_PAYMENT.PAYMENT_VALUE, payment.getPaymentValue())
                .set(DB_PAYMENT.PAID, payment.isPaid())
                .set(DB_PAYMENT.COMMENT, payment.getComment())
                .set(DB_PAYMENT.INCOMING, payment.isIncoming())
                .set(DB_PAYMENT.PAID_DATE, payment.getPaymentDate())
                .set(DB_PAYMENT.US, payment.isUs())
                .where(DB_PAYMENT.PAYMENT_ID.eq(payment.getPaymentId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_PAYMENT)
                .where(DB_PAYMENT.PAYMENT_ID.eq(id))
                .execute();
    }
}
