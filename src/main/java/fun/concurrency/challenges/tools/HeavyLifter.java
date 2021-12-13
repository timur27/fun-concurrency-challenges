package fun.concurrency.challenges.tools;

import java.util.concurrent.CompletionStage;

/**
 * Represents set of methods to lift heavy processing in asynchronous manner.
 *
 @param <T> the type of the input
 * @param <R> the type of the output
 */
public interface HeavyLifter<T, R> {

    /**
     * Performs the computation asynchronously
     *
     * @param heavyStuff input object
     * @return {@link CompletionStage} which gives an option for the client to extend computation stages
     */
    CompletionStage<R> compute(T heavyStuff);
}
