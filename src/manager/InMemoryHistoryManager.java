package manager;

import manager.interfaces.HistoryManager;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager {
    private Node first;
    private Node last;
    private final HashMap<Integer, Node> historyTask = new HashMap<>();//Хранит последние 10 просмотренных пользователем задач.

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (historyTask.containsKey(task.getId())) {//задача должна быть в единственном экземпляре в истории, если есть удаляем
            Node deleteNode = historyTask.get(task.getId());
            removeNode(deleteNode);
        }

        if (historyTask.size() == 10) {//Проверяет, чтобы в historyTask было не больше 10 Задач
            removeNode(first);
        }

        linkLast(task);
    }

    @Override
    public void removeById(int id) {

        if (historyTask.containsKey(id)) {
            removeNode(historyTask.get(id));
        }

        historyTask.remove(id);
    }

    @Override
    public void removeTaskAll(Map<Integer, Task> tasksMap) {

        for (Map.Entry<Integer, Task> entry : tasksMap.entrySet()) {
            if (historyTask.containsKey(entry.getKey())) {
                removeNode(historyTask.get(entry.getKey()));
                historyTask.remove(entry.getKey());
            }
        }
    }

    @Override
    public void removeEpicAll(Map<Integer, Epic> epicsMap) {

        for (Map.Entry<Integer, Epic> entry : epicsMap.entrySet()) {
            if (historyTask.containsKey(entry.getKey())) {
                removeNode(historyTask.get(entry.getKey()));
                historyTask.remove(entry.getKey());
            }
        }
    }

    @Override
    public void removeSubtaskAll(Map<Integer, Subtask> subtasksMap) {

        for (Map.Entry<Integer, Subtask> entry : subtasksMap.entrySet()) {
            if (historyTask.containsKey(entry.getKey())) {
                removeNode(historyTask.get(entry.getKey()));
                historyTask.remove(entry.getKey());
            }
        }
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
            last.prev = first;
            first.next = last;
            historyTask.put(task.getId(), nodeNew);
        } else {
            nodeNew.prev = last;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // если ссылки на один и тот же объект
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(next, node.next) &&
                    Objects.equals(prev, node.prev) &&
                    Objects.equals(task, node.task);
        }

        @Override
        public int hashCode() {
            return Objects.hash(next, prev, task);
        }
    }
}