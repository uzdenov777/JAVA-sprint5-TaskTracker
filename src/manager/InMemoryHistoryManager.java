package manager;

import manager.interfaces.HistoryManager;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private Node first;
    private Node last;
    private final HashMap<Integer, Node> historyTask = new HashMap<>();//Хранит последние 10 просмотренных пользователем задач.

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (historyTask.containsKey(task.getId())) {
            Node deleteNode = historyTask.get(task.getId());
            removeNode(deleteNode);//задача должна быть в единственном экземпляре в истории, если есть удаляем
        }

        if (historyTask.size() == 10) {//Проверяет, чтобы в historyTask было не больше 10 Задач
            int idDeletingNode = first.task.getId();
            first = first.next;
            historyTask.remove(idDeletingNode);
        }

        linkLast(task);
    }

    @Override
    public void removeById(int id) {

        historyTask.remove(id);
    }

    @Override
    public void removeTaskAll(Map<Integer, Task> tasksMap) {
        historyTask.keySet().removeAll(tasksMap.keySet());
    }

    @Override
    public void removeEpicAll(Map<Integer, Epic> epicsMap) {
        historyTask.keySet().removeAll(epicsMap.keySet());
    }

    @Override
    public void removeSubtaskAll(Map<Integer, Subtask> subtasksMap) {
        historyTask.keySet().removeAll(subtasksMap.keySet());
    }

    @Override
    public HashMap<Integer, Node> getTasksHistoryInMap() {
        return historyTask;
    }

    @Override
    public ArrayList<Task> getListHistory() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }

    @Override
    public void linkLast(Task task) {
        Node nodeNew = new Node(last, task);
        if (first == null) {
            first = nodeNew;
            historyTask.put(task.getId(), nodeNew);
        } else if (last == null) {
            last = nodeNew;
            first.next = last;
            historyTask.put(task.getId(), nodeNew);
        } else {
            last.next = nodeNew;
            last = nodeNew;
            historyTask.put(task.getId(), nodeNew);
        }
    }

    @Override
    public void removeNode(Node node) {
        int idDeletingNode = node.task.getId();

        if (first == node) {
            first = first.next;
            first.prev = null;
            historyTask.remove(idDeletingNode);
        } else if (last == node) {
            last = last.prev;
            last.next = null;
            historyTask.remove(idDeletingNode);
        } else {
            Node previous = node.prev;
            Node next = node.next;
            previous.next = next;
            next.prev = previous;
            historyTask.remove(idDeletingNode);
        }
    }

    public class Node {
        Node next = null;
        Node prev;
        Task task;

        public Node(Node prev, Task task) {
            this.prev = prev;
            this.task = task;
        }
    }
}