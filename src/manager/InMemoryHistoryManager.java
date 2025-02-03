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
        historyTask.remove(task.getId());//задача должна быть в единственном экземпляре в истории, если есть удаляем

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
        for (Integer taskId : tasksMap.keySet()) {
            historyTask.remove(taskId);
        }
    }

    @Override
    public void removeEpicAll(Map<Integer, Epic> epicsMap) {
        for (Integer taskId : epicsMap.keySet()) {
            historyTask.remove(taskId);
        }
    }

    @Override
    public void removeSubtaskAll(Map<Integer, Subtask> subtasksMap) {
        for (Integer taskId : subtasksMap.keySet()) {
            historyTask.remove(taskId);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
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
        Node node = new Node(null, last, task);

        if (first == null) {
            first = node;
        } else {
            last.next = node;
            last = node;
        }
        historyTask.put(task.getId(), node);
    }

    @Override
    public void removeNode(Node node) {
        if (first == node) {
            int idDeletingNode = first.task.getId();
            first = first.next;
            historyTask.remove(idDeletingNode);
        } else if (last == node) {
            int idDeletingNode = last.task.getId();
            last = last.prev;
            historyTask.remove(idDeletingNode);
        } else {
            int idDeletingNode = first.task.getId();
            Node previous = node.prev;
            Node next = node.next;
            previous.next = next;
            next.prev = previous;
            historyTask.remove(idDeletingNode);
        }
    }

    public class Node {
        Node next;
        Node prev;
        Task task;

        public Node(Node next, Node prev, Task task) {
            this.next = next;
            this.prev = prev;
            this.task = task;
        }
    }
}


