package fun.concurrency.challenges.usecases.heavycomputations;

import fun.concurrency.challenges.tools.HeavyLifter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

public class ComputationsExecutor implements HeavyLifter<List<String>, Long> {

    private final Map<String, Integer> sum = new HashMap<>();

    @Override
    public CompletionStage<Long> compute(List<String> heavyStuff) {
        return CompletableFuture.supplyAsync(() -> heavyStuff)
                .thenApply(strings -> strings.stream().map(String::length).reduce(0, Integer::sum, Integer::sum))
                .thenAccept(addResult());
    }


    private Consumer<Integer> addResult() {
        return sum -> this.sum.put(UUID.randomUUID().toString(), sum);
    }
}
