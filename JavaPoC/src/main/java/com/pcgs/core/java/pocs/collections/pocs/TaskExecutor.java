package com.pcgs.core.java.pocs.collections.pocs;

import java.util.PriorityQueue;

public class TaskExecutor {
    private PriorityQueue<Task> queue;

    public TaskExecutor() {
        queue = new PriorityQueue<>(); // uses Task's compareTo()
    }

    public void addTask(Task task) {
        queue.offer(task);
    }

    public Task executeTask() {
        return queue.poll(); // Removes and returns highest-priority task
    }

    public boolean hasTasks() {
        return !queue.isEmpty();
    }

    public int taskCount() {
        return queue.size();
    }
}
