package ru.yandex.practicum.gym;

public class Group {
    //название группы
    private String title;
    //тип (взрослая или детская)
    private Age age;
    //длительность (в минутах)
    private int duration;

    public Group(String title, Age age, int duration) {
        this.title = title;
        this.age = age;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public Age getAge() {
        return age;
    }

    public String getTitle() {
        return title;
    }
}
