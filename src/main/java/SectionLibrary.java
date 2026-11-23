
package main.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SectionLibrary {
    private LinkedHashMap<String, ArrayList<String>> sections;

    public SectionLibrary() {
        this.sections = new LinkedHashMap<>();

        sections.put("Daily Reflection", new ArrayList<>());
        sections.get("Daily Reflection").add("Today's Thoughts");
        sections.get("Daily Reflection").add("Daily Reflection");
        sections.get("Daily Reflection").add("Highlights of the Day");
        sections.get("Daily Reflection").add("Lowlights of the Day");

        sections.put("Emotions", new ArrayList<>());
        sections.get("Emotions").add("How I'm Feeling");
        sections.get("Emotions").add("Emotional Check-In");
        sections.get("Emotions").add("What Made Me Happy");
        sections.get("Emotions").add("What's Bothering Me");

        sections.put("Thankfulness", new ArrayList<>());
        sections.get("Thankfulness").add("Things I'm Thankful For");
        sections.get("Thankfulness").add("People I Appreciate");
        sections.get("Thankfulness").add("Small Things I Enjoy");

        sections.put("Personal Growth", new ArrayList<>());
        sections.get("Personal Growth").add("Lessons Learned");
        sections.get("Personal Growth").add("Personal Accomplishments");
        sections.get("Personal Growth").add("Areas to Improve");
        sections.get("Personal Growth").add("Things I Discovered");

        sections.put("Goals and Plans", new ArrayList<>());
        sections.get("Goals and Plans").add("Today's Goals");
        sections.get("Goals and Plans").add("Future Goals");
        sections.get("Goals and Plans").add("Priorities");
        sections.get("Goals and Plans").add("Things to Look Forward To");

        sections.put("Relationships", new ArrayList<>());
        sections.get("Relationships").add("People on My Mind");
        sections.get("Relationships").add("Meaningful Conversations");
        sections.get("Relationships").add("Relationships");

        sections.put("Creativity", new ArrayList<>());
        sections.get("Creativity").add("Ideas");
        sections.get("Creativity").add("Creative Thoughts");
        sections.get("Creativity").add("Dreams");
        sections.get("Creativity").add("Random Thoughts");

        sections.put("Free Writing", new ArrayList<>());
        sections.get("Free Writing").add("Brain Dump");
        sections.get("Free Writing").add("Unfiltered Thoughts");
        sections.get("Free Writing").add("Anything on My Mind");
    }

    public ArrayList<String> getCategories() {
        return new ArrayList<>(sections.keySet());
    }

    public ArrayList<String> getSections(String category) {
        ArrayList<String> categorySections = sections.get(category);

        if (categorySections == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(categorySections);
    }
}
