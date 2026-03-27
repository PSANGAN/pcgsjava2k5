package com.pcgs.core.java.pocs.collections.pocs;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private int id;
    private int priority;
    private LocalDate dueDate;

    public Task(int id, int priority, LocalDate dueDate) {
        this.id = id;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public int compareTo(Task other) {
        // Sort by priority DESC
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }
        // If priority is the same, sort by dueDate ASC
        return this.dueDate.compareTo(other.dueDate);
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", priority=" + priority + ", dueDate=" + dueDate + "}";
    }
}

