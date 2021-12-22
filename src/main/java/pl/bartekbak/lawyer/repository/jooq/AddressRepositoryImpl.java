package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbAddress.DB_ADDRESS;

@Repository
public class AddressRepositoryImpl extends DatabaseContext implements AddressRepository {

    public AddressRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Address> list() {
        return dslContext().selectFrom(DB_ADDRESS)
                .fetch()
                .map(Address::fromDbRecord);
    }

    @Override
    public Optional<Address> addressById(int id) {
        return dslContext().selectFrom(DB_ADDRESS)
                .where(DB_ADDRESS.ADDRESS_ID.eq(id))
                .fetchOptional()
                .map(Address::fromDbRecord);
    }

    @Override
    @Transactional
    public void add(Address address) {
        dslContext().insertInto(DB_ADDRESS)
                .set(DB_ADDRESS.ADDRESS_ID, address.getAddressId())
                .set(DB_ADDRESS.CITY, address.getCity())
                .set(DB_ADDRESS.COUNTRY, address.getCountry())
                .set(DB_ADDRESS.STREET, address.getStreet())
                .set(DB_ADDRESS.ZIP_CODE, address.getZipCode())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Address address) {
        dslContext().update(DB_ADDRESS)
                .set(DB_ADDRESS.CITY, address.getCity())
                .set(DB_ADDRESS.COUNTRY, address.getCountry())
                .set(DB_ADDRESS.STREET, address.getStreet())
                .set(DB_ADDRESS.ZIP_CODE, address.getZipCode())
                .where(DB_ADDRESS.ADDRESS_ID.eq(address.getAddressId()))
                .andNotExists(dslContext().selectFrom(DB_ADDRESS)
                        .where(DB_ADDRESS.CITY.eq(address.getCity()))
                        .and(DB_ADDRESS.STREET.eq(address.getStreet()))
                        .and(DB_ADDRESS.COUNTRY.eq(address.getCountry())))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_ADDRESS)
                .where(DB_ADDRESS.ADDRESS_ID.eq(id))
                .execute();
    }
}
