package manager;

import manager.interfaces.HistoryManager;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyTask = new ArrayList<>();//Хранит последние 10 просмотренных пользователем задач.

    @Override
    public void add(Task task) {//Проверяет, чтобы в historyTask было не больше 10 Задач
        if (historyTask.size() == 10) {
            historyTask.remove(0);
        }
        historyTask.add(task);
    }

    @Override
    public void removeById(int id) {
        for (Task task : historyTask) {
            if (task.getId() == id) {
                historyTask.remove(task);
            }
        }
    }

    @Override
    public void removeAll(Map map) {
        historyTask.removeAll(map.values());
    }

    @Override
    public List<Task> getHistory() {
        return historyTask;
    }
}
