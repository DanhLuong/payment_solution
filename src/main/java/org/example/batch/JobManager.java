package org.example.batch;

import java.util.HashMap;
import java.util.Map;

public class JobManager {
    private final Map<String, Job> jobs = new HashMap<>();
    private boolean blocked = false;
    public void addJob(String name, Job job) {
        if(blocked) {
            return;
        }
        jobs.put(name, job);
    };
    public Job getJob(String name) {
        return jobs.getOrDefault(name.trim(), null);
    }
    public void doneConfig() {
        blocked = true;
    }
    public void startAllJobs() {
        for (Job job : jobs.values()) {
            job.start();
        }
    }
}
