package task;

import java.util.HashMap;
import java.util.Objects;

public class Epic extends Task {
    private final HashMap<Integer, Subtask> subtaskArray = new HashMap<>();

    public Epic(String name, String description, int id, StatusTask status) {
        super(name, description, id, status);
    }

    public void addSubtask(Subtask subtask) {
        subtaskArray.put(subtask.getId(), subtask);
    }

    public HashMap<Integer, Subtask> getSubtasksArray() {
        return subtaskArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskArray, epic.subtaskArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskArray);
    }
}
