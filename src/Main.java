import manager.Managers;
import manager.interfaces.TaskManager;
import task.Epic;
import task.StatusTask;
import task.Subtask;
import task.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("task1", "task1task1", manager.getNewId(), StatusTask.NEW);
        Task task2 = new Task("task2", "task2task2", manager.getNewId(),  StatusTask.NEW);
        Epic epic1 = new Epic("epic1", "epic1epic1", manager.getNewId(),  StatusTask.NEW);
        Epic epic2 = new Epic("epic2", "epic2epic2", manager.getNewId(),  StatusTask.NEW);
        Subtask subtask1 = new Subtask("subtask1", "subtask1subtask1", manager.getNewId(),  StatusTask.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("subtask2", "subtask2subtask2", manager.getNewId(),  StatusTask.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("subtask3", "subtask3subtask3", manager.getNewId(),  StatusTask.NEW, epic1.getId());
        Task task3 = new Task("task3", "task3task3", manager.getNewId(),  StatusTask.NEW);
        Epic epic3 = new Epic("epic3", "epic3epic3", manager.getNewId(),  StatusTask.NEW);
        Epic epic4 = new Epic("epic4", "epic4epic4", manager.getNewId(),  StatusTask.NEW);
        Subtask subtask4 = new Subtask("subtask4", "subtask4subtask4", manager.getNewId(),  StatusTask.NEW, epic3.getId());
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.addEpic(epic3);
        manager.addEpic(epic4);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);
        manager.addSubtask(subtask4);


        System.out.println("Epic");
        System.out.println(manager.getEpics());
        System.out.println("Subtask");
        System.out.println(manager.getSubtasks());
        System.out.println("History");
        System.out.println(manager.getHistory());

        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic2.getId()));
        System.out.println(manager.getSubtask(subtask1.getId()));
        System.out.println(manager.getSubtask(subtask2.getId()));//1
        System.out.println(manager.getSubtask(subtask3.getId()));//2
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic1.getId()));//3
        System.out.println(manager.getSubtask(subtask1.getId()));//4
        System.out.println(manager.getTask(task1.getId()));//5
        System.out.println(manager.getTask(task2.getId()));//6
        System.out.println(manager.getTask(task3.getId()));//7
        System.out.println(manager.getEpic(epic3.getId()));//8
        System.out.println(manager.getEpic(epic4.getId()));//9
        System.out.println(manager.getSubtask(subtask4.getId()));//10

        for(Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println(manager.getHistory().size());

        manager.removeEpicById(epic1.getId());

        for(Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println(manager.getHistory().size());
    }
}