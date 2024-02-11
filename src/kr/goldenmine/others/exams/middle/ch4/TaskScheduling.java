package kr.goldenmine.others.exams.middle.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskScheduling {
    static class Task {
        int start;
        int finish;

        public Task(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "start=" + start +
                    ", finish=" + finish +
                    '}';
        }
    }

    static class Schedule {
        List<Task> tasks = new ArrayList<>();
        int prevFinishTime = -1;
    }

    // https://st-lab.tistory.com/145
    public static void main(String[] args) {
        Schedule[] schedules = new Schedule[10];
        for(int i = 0; i <schedules.length; i++) {
            schedules[i] = new Schedule();
        }

        //  T={t1=[7,8], t2=[3,7], t3=[1,5], t4=[5,9], t5=[0,2], t6=[6,8], t7=[1,6]}
        Task[] tasks = {
                new Task(7, 8),
                new Task(3, 7),
                new Task(1, 5),
                new Task(5, 9),
                new Task(0, 2),
                new Task(6, 8),
                new Task(1, 6),
        };

        // Sort them by ascending order of starting time
        Arrays.sort(tasks, (o1, o2) -> {
//            if(o1.start == o2.start) {
//                return Integer.compare(o1.finish, o2.finish);
//            }

            return Integer.compare(o1.start, o2.start);
        });

        System.out.println("All Tasks: " + Arrays.toString(tasks));

        // while(L ≠ ∅) {
        for(int i = 0; i < tasks.length; i++) {
            // ti = L[0]
            Task task = tasks[i];

            for(int j = 0; j <schedules.length; j++) {
                // m’ = {ti}
                Schedule schedule = schedules[j];

                // if(isRunnable(ti ,m) for m in M)
                if(schedule.prevFinishTime <= task.start) {
                    schedule.tasks.add(task);
                    schedule.prevFinishTime = task.finish;

                    System.out.println("schedule " + (j + 1) + " inserts " + task);

                    break;
                }
            }
        }

        for(int i = 0; i < schedules.length; i++) {
            Schedule schedule = schedules[i];
            if(schedule.tasks.size() == 0) break;

            System.out.println("schedule " + (i + 1) + ": " + schedule.tasks);
        }
    }
}
