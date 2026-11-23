package main.java;

import java.util.ArrayList;

public class SectionLibrary {
    private ArrayList<String> sections;

    public SectionLibrary() {
        this.sections = new ArrayList<>();


        sections.add("Today's Thoughts");
        sections.add("Daily Reflection");
        sections.add("Highlights of the Day");
        sections.add("Lowlights of the Day");


        sections.add("How I'm Feeling");
        sections.add("Emotional Check-In");
        sections.add("What Made Me Happy");
        sections.add("What's Bothering Me");


        sections.add("Things I'm Grateful For");
        sections.add("People I Appreciate");
        sections.add("Small Things I Enjoy");


        sections.add("Lessons Learned");
        sections.add("Personal Accomplishments");
        sections.add("Areas to Improve");
        sections.add("Things I Discovered");


        sections.add("Today's Goals");
        sections.add("Future Goals");
        sections.add("Priorities");
        sections.add("Things to Look Forward To");


        sections.add("People on My Mind");
        sections.add("Meaningful Conversations");
        sections.add("Relationships");


        sections.add("Ideas");
        sections.add("Creative Thoughts");
        sections.add("Dreams");
        sections.add("Random Thoughts");


        sections.add("Brain Dump");
        sections.add("Unfiltered Thoughts");
        sections.add("Anything on My Mind");
    }

    public ArrayList<String> getSections() {
        return new ArrayList<>(sections);
    }
}
