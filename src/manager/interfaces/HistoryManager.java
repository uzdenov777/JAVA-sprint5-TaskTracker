package manager.interfaces;

import task.Task;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

    void add(Task task);

    void removeById(int id);

    void removeAll(Map map);

    List<Task> getHistory();

}
