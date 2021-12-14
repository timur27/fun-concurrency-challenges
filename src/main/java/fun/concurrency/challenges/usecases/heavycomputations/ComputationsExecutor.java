package fun.concurrency.challenges.usecases.heavycomputations;

import fun.concurrency.challenges.tools.HeavyLifter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ComputationsExecutor implements HeavyLifter<List<String>, Long> {

    @Override
    public CompletableFuture<Long> compute(List<String> heavyStuff) {
        return CompletableFuture.supplyAsync(() -> heavyStuff)
                .thenApplyAsync(this::mapStringsToLength)
                .thenApplyAsync(this::sumIntegers);
    }

    private List<Integer> mapStringsToLength(List<String> in) {
        return in.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }

    private Long sumIntegers(List<Integer> in) {
        sleepABit();
        return in.stream().reduce(0, Integer::sum, Integer::sum)
                .longValue();
    }

    private void sleepABit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
