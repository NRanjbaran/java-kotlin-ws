package util;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InstantUtil {

    public List<Instant> createInstantsBetweenTime(Instant start, Instant end, int instantCountPerMinute) {
        return createRandomInstantsPerMinute(start, end, instantCountPerMinute);
    }

    public List<Instant> createRandomInstantsPerMinute(Instant startInclusive, Instant endInclusive, int instantCountPerMinute) {
        List<Instant> results = new ArrayList<>();
        Instant start = startInclusive;
        Instant end = startInclusive.plus(1, ChronoUnit.MINUTES);
        long differenceMinutes = Duration.between(startInclusive, endInclusive).toMinutes();
        for (int i = 0; i < differenceMinutes; i++) {
            List<Instant> instantsPerMinute = createRandomInstants(start, instantCountPerMinute);

            results.addAll(instantsPerMinute);
            start = start.plus(1, ChronoUnit.MINUTES);
            end = start.plus(1, ChronoUnit.MINUTES);
        }
        return results;
    }

    public List<Instant> createRandomInstants(Instant startInclusive, int instantCount) {
        List<Instant> instants = new ArrayList<>();
        for (int i = 1; i <= instantCount; i++) {
            instants.add(startInclusive.plusSeconds(i));
        }
        return instants;
    }
}
