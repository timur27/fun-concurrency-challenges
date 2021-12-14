package fun.concurrency.challenges.usecases.heavycomputations;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class ComputationsExecutorTest {

    private final ComputationsExecutor underTest = new ComputationsExecutor();
    private final MainFlow mainFlow = new MainFlow();

    @Test
    public void test_heavyComputations_emptyList() throws ExecutionException, InterruptedException {
        final var future = underTest.compute(List.of());

        assertThat(mainFlow.executeMainFlow()).isEqualTo(Integer.MAX_VALUE);
        assertThat(future.get()).isEqualTo(0);
    }

    // TODO Implement the case, when CompletableFutura has the stage with not async procesing.
    //  Should the main thread execution be stopped?
}