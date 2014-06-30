package org.nem.nis.j;

import java.util.List;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.time.TimeInstant;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.j.vs;

public interface zh
extends vs {
    public void a(Block var1);

    public void b(Block var1);

    public List<c> a(BlockHeight var1, int var2);

    public List<TimeInstant> b(BlockHeight var1, int var2);

    public void r(BlockHeight var1);
}