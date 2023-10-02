package postgre.goal.dataGenerator.service;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public interface DataCreator<R, K> {
    List<R> createDataBatchWithForeignKeys(Supplier<Set<K>> foreignKeysSupplier);

    List<R> createDataBatch();
}
