package day4;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReposeRecord {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Map<Instant, String> mapChronologicalEvents = new TreeMap<>();
    Map<Integer, TreeSet<Instant>> guardSleepingTimes = new TreeMap<>();
    private Integer currentID = 0;
    private Instant fallenAsleep;
    private SleepingEventParser sleepingEventParser = event ->
    {
        String dateString = event.substring(event.indexOf("[") + 1, event.indexOf("]"));
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return (ZonedDateTime.of(localDateTime, ZoneId.systemDefault())).toInstant();
    };

    public EventConsumer factoryConsumer =  event -> {
        Instant eventInstant = sleepingEventParser.parseDate(event);
        String eventString = event.substring(event.indexOf("]"));
        mapChronologicalEvents.put(eventInstant, eventString);
    };

    private void parseEvent(Instant eventInstant, String eventString) {
        if (eventString.contains("begins shift")) {
            this.currentID = Arrays.stream(eventString.split(" "))
                    .filter(s -> s.contains("#"))
                    .mapToInt(s -> Integer.parseInt(s.substring(1)))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        } else if (eventString.contains("falls asleep")) {
            this.fallenAsleep = eventInstant;
        } else if (eventString.contains("wakes up")) {
            Instant sleepStart = fallenAsleep;
            TreeSet<Instant> sleepingSet = new TreeSet<>();
            Instant sleeping = sleepStart;
            while (sleeping.isBefore(eventInstant)) {
                sleepingSet.add(sleeping);
                sleeping = sleeping.plus(1, ChronoUnit.MINUTES);
            }
            if (guardSleepingTimes.containsKey(currentID)) {
                Set<Instant> sleepingInstants = guardSleepingTimes.get(currentID);
                sleepingInstants.addAll(sleepingSet);
            } else {
                guardSleepingTimes.put(currentID, sleepingSet);
            }
        }
    }

    int mostOftenSleep(){
        mapChronologicalEvents.forEach(this::parseEvent);
        Map<Integer, Integer> minutesSlept = guardSleepingTimes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
        return minutesSlept.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
    }

    int getMinuteOfSleep(int guardId) {
        return guardSleepingTimes.get(guardId).stream()
                .map(instant -> instant.atZone(ZoneId.systemDefault()).getMinute())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseThrow(RuntimeException::new);
    }

    int getPartTwo() {
        Map<Integer, Integer> guardsToMostFrequentMinute = guardSleepingTimes.entrySet().stream()
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> getMinuteOfSleep(e.getKey())
        ));
        int lazyguard = guardsToMostFrequentMinute.entrySet().stream()
                .max((entry1, entry2) -> {
                    Integer entry1Occurences = getOccurences(entry1.getKey(), entry1.getValue());
                    Integer entry2Occurences = getOccurences(entry2.getKey(), entry2.getValue());
                    return entry1Occurences.compareTo(entry2Occurences);
                }).get().getKey();
        return lazyguard * guardsToMostFrequentMinute.get(lazyguard);
    }

    private int getOccurences(Integer guardId, Integer minute) {
        TreeSet<Instant> instants = guardSleepingTimes.get(guardId);
        List<Integer> minutes = instants.stream().map(instant -> instant.atZone(ZoneId.systemDefault()).getMinute()).collect(Collectors.toList());
        return Collections.frequency(minutes, minute);
    }

    public int getPartOne() {
        int sleepyGuardId = mostOftenSleep();
        return getMinuteOfSleep(sleepyGuardId) * sleepyGuardId;
    }

    @FunctionalInterface
    interface SleepingEventParser {
        Instant parseDate(String event);
    }

    @FunctionalInterface
    interface EventConsumer {
        void consume(String event);
    }
}
