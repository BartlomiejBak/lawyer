package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.repository.TagRepository;

import java.util.List;
import java.util.Optional;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbTag.DB_TAG;

@Repository
public class TagRepositoryImpl extends DatabaseContext implements TagRepository {

    public TagRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Tag> list() {
        return dslContext().selectFrom(DB_TAG)
                .fetch()
                .map(Tag::fromDbRecord);
    }

    @Override
    public Optional<Tag> tagById(int id) {
        return dslContext().selectFrom(DB_TAG)
                .where(DB_TAG.TAG_ID.eq(id))
                .fetchOptional()
                .map(Tag::fromDbRecord);
    }

    @Override
    @Transactional
    public void add(Tag tag) {
        dslContext().insertInto(DB_TAG)
                .set(DB_TAG.NAME, tag.getName())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Tag tag) {
        dslContext().update(DB_TAG)
                .set(DB_TAG.NAME, tag.getName())
                .where(DB_TAG.TAG_ID.eq(tag.getTagId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_TAG)
                .where(DB_TAG.TAG_ID.eq(id))
                .execute();
    }
}
