package org.WM.ToDoApplication.Models;

public class Task {
    int taskId;
    String taskName;
    String deadline;
    boolean isCompleted = false;
    int priorityId;

    public Task() {
        //
    }

    public Task(String taskName, String deadline, int priorityId) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.priorityId = priorityId;
    }

    public Task(int taskId, String taskName, String deadline, int priorityId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.deadline = deadline;
        this.priorityId = priorityId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "\n taskId=" + taskId +
                ",\n taskName='" + taskName + '\'' +
                ",\n deadline='" + deadline + '\'' +
                ",\n isCompleted=" + isCompleted +
                ",\n priorityId=" + priorityId +
                "\n}";
    }
}
