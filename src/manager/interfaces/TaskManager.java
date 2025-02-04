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

    List<Epic> getEpics(); //Получение списка всех Epic

    List<Subtask> getSubtasks(); //Получение списка всех подзадач

    void clearTasks(); //Удаление всех задач.

    void clearEpics(); //Удаление всех Epic.

    void clearSubtasks(); //Удаление всех подзадач.

    Optional<Task> getTask(int id); //Получение ask по идентификатору

    Optional<Epic> getEpic(int id); //Получение Epic по идентификатору

    Optional<Subtask> getSubtask(int id); //Получение Subtask по идентификатору

    void addTask(Task taskInput); //Добавляет полученный объект Task в соответсвующий HashMap и проверяет, если такой ID уже

    void addEpic(Epic taskInput); //Добавляет полученный объект Epic в соответсвующий HashMap и проверяет, если такой ID уже

    void addSubtask(Subtask taskInput); //Добавляет полученный объект Subtask в соответсвующий HashMap и проверяет, если такой ID уже

    void updateTask(Task taskInput); // Обновление Task. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void updateEpic(Epic taskInput); // Обновление Epic. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void updateSubtask(Subtask taskInput); // Обновление Subtask. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    void removeTaskById(int id);//Удаление Task по идентификатору.

    void removeEpicById(int id); //Удаление Epic по идентификатору и его подзадачи.

    void removeSubtaskById(int id);//Удаление Subtask по идентификатору.

    HashMap<Integer, Subtask> getListSubtasks(int id); //Получение списка всех подзадач определённого Epic.

    int getNewId();

    ArrayList<Task> getHistory();
}