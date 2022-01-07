package pl.bartekbak.lawyer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jooq.DSLContext;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class DatabaseContext {

    private final DSLContext dslContext;
}
