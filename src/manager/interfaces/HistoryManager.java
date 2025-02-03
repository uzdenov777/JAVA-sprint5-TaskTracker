package manager.interfaces;

import manager.InMemoryHistoryManager.Node;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.Map;

public interface HistoryManager {

    void add(Task task);

    void removeById(int id);

    void removeTaskAll(Map<Integer, Task> tasksMap);

    void removeEpicAll(Map<Integer, Epic> epicsMap);

    void removeSubtaskAll(Map<Integer, Subtask> subtasksMap);

    ArrayList<Task> getHistory();

    void linkLast(Task task);

    void removeNode(Node node);

}
