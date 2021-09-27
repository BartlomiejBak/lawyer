package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbNote.DB_NOTE;

@Repository
public class NoteRepositoryImpl extends DatabaseContext implements NoteRepository {

    public NoteRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Note> list() {
        return dslContext().selectFrom(DB_NOTE)
                .fetch()
                .map(Note::fromDbRecord);
    }

    @Override
    public Optional<Note> noteById(int id) {
        return dslContext().selectFrom(DB_NOTE)
                .where(DB_NOTE.NOTE_ID.eq(id))
                .fetchOptional()
                .map(Note::fromDbRecord);
    }

    @Override
    @Transactional
    public void add(Note note) {
        dslContext().insertInto(DB_NOTE)
                .set(DB_NOTE.TITLE, note.getTitle())
                .set(DB_NOTE.TEXT, note.getText())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Note note) {
        dslContext().update(DB_NOTE)
                .set(DB_NOTE.TITLE, note.getTitle())
                .set(DB_NOTE.TEXT, note.getText())
                .where(DB_NOTE.NOTE_ID.eq(note.getNoteId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_NOTE)
                .where(DB_NOTE.NOTE_ID.eq(id))
                .execute();
    }
}
