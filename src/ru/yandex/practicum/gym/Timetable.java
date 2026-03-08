package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {

        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayTrainings = timetable.get(dayOfWeek);
        if (Objects.isNull(dayTrainings)) {
            dayTrainings = new TreeMap<>();
            timetable.put(dayOfWeek, dayTrainings);
        }
        List<TrainingSession> trainingSessions = dayTrainings.get(timeOfDay);
        if (Objects.isNull(trainingSessions)) {
            trainingSessions = new ArrayList<>();
            dayTrainings.put(timeOfDay, trainingSessions);
        }
        trainingSessions.add(trainingSession);
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> training = timetable.get(dayOfWeek);
        return Objects.nonNull(training) ? training : new TreeMap<>();
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay,List<TrainingSession>> dayTraining = timetable.get(dayOfWeek);
        if (Objects.isNull(dayTraining)) {
            return new ArrayList<>();
        }
        List<TrainingSession> timeTrainings = dayTraining.get(timeOfDay);
        return Objects.nonNull(timeTrainings) ? timeTrainings : new ArrayList<>();
    }

    public List<TrainingSession> getAllSessions() {
        List<TrainingSession> all = new ArrayList<>();
        for (TreeMap<TimeOfDay,List<TrainingSession>> dayTrainings : timetable.values()) {
            for (List<TrainingSession> timeSessions : dayTrainings.values()) {
                all.addAll(timeSessions);
            }
        }
        return all;
    }

    public List<Map.Entry<Coach,Integer>> getCountByCoaches() {
        List<TrainingSession> allSessions = getAllSessions();

        if (allSessions.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Coach,Integer> count = new HashMap<>();

        for (TrainingSession session : allSessions) {
            Coach coach = session.getCoach();
            count.put(coach,count.getOrDefault(coach, 0) + 1);
        }
        List<Map.Entry<Coach,Integer>> result = new ArrayList<>(count.entrySet());
        result.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        return result;
    }
}
