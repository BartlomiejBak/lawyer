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
    public int add(Tag tag) {
        return dslContext().insertInto(DB_TAG)
                .set(DB_TAG.TAG_ID, tag.getTagId())
                .set(DB_TAG.NAME, tag.getName())
                .onDuplicateKeyIgnore()
                .returningResult(DB_TAG.TAG_ID)
                .execute();
    }

    @Override
    @Transactional
    public void update(Tag tag) {
        dslContext().update(DB_TAG)
                .set(DB_TAG.NAME, tag.getName())
                .where(DB_TAG.TAG_ID.eq(tag.getTagId()))
                .andNotExists(dslContext().selectFrom(DB_TAG)
                        .where(DB_TAG.NAME.eq(tag.getName())))
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
