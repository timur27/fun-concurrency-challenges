package fun.concurrency.challenges.usecases.heavycomputations;

public class MainFlow {
    public Integer executeMainFlow() {
        System.out.println("Main Flow has been executed");
        return Integer.MAX_VALUE;
    }
}
