package postgre.goal.dataGenerator.creator;

import java.util.List;

public interface DataCreator<R> {
    List<R> createDataBatch();
}
