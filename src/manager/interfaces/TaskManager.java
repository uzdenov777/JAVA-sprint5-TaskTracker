package manager.interfaces;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TaskManager {

    List<Task> getTasks(); //Получение списка всех задач

    List<Epic> getEpics(); //Получение списка всех task.Epic

    List<Subtask> getSubtasks(); //Получение списка всех подзадач

    void clearTasks(); //Удаление всех задач.

    void clearEpics(); //Удаление всех task.Epic.

    void clearSubtasks(); //Удаление всех подзадач.

    Optional<Task> getTask(int id); //Получение task.Task по идентификатору

    Optional<Epic> getEpic(int id); //Получение task.Epic по идентификатору

    Optional<Subtask> getSubtask(int id); //Получение task.Subtask по идентификатору

    void addTask(Task taskInput); //Добавляет полученный объект task.Task в соответсвующий HashMap и проверяет, если такой ID уже

    void addEpic(Epic taskInput); //Добавляет полученный объект task.Epic в соответсвующий HashMap и проверяет, если такой ID уже

    void addSubtask(Subtask taskInput); //Добавляет полученный объект task.Subtask в соответсвующий HashMap и проверяет, если такой ID уже

    void updateTask(Task taskInput); // Обновление task.Task. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void updateEpic(Epic taskInput); // Обновление task.Epic. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void updateSubtask(Subtask taskInput); // Обновление task.Subtask. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void removeTaskById(int id);//Удаление task.Task по идентификатору.

    void removeEpicById(int id); //Удаление task.Epic по идентификатору и его подзадачи.

    void removeSubtaskById(int id);//Удаление task.Subtask по идентификатору.

    HashMap<Integer, Subtask> getListSubtasks(int id); //Получение списка всех подзадач определённого task.Epic.

    int getNewId();

    ArrayList<? extends Task> getHistory();
}