package Labs.Lab10;

import java.io.*;
import java.util.*;

class Film implements Serializable {
    private String title;
    private int episodes;
    private int duration;
    private List<String> days;

    public Film(String title, int episodes, int duration, List<String> days) {
        this.title = title;
        this.episodes = episodes;
        this.duration = duration;
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getDays() {
        return days;
    }

    @Override
    public String toString() {
        return "Фильм: " + title + ", Серий: " + episodes + ", Длительность: " + duration + " мин, Дни показа: " + days;
    }
}