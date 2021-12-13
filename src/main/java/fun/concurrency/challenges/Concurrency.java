package fun.concurrency.challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Concurrency {

    public static void main(String[] args){
        final var sandbox = new Sandbox();
        sandbox.game();
    }


    static class Sandbox {
        Map<String, String> justPlay = new HashMap<>();

        public void game() {
            CompletableFuture<Void> asyncTask = CompletableFuture.supplyAsync(this::getString)
                    .exceptionally(err -> "string")
                    .thenAccept(this::addToMap);

            while (!asyncTask.isDone()) {
                System.out.println("Waiting for the result...");
            }

            System.out.println(this.getMap());
        }


        private void addToMap(String result) {
            justPlay.put(UUID.randomUUID().toString(), result);
        }

        private Map<String, String> getMap() {
            return new HashMap<>(this.justPlay);
        }

        private String getString() {
            if (false) {
                throw new NullPointerException("that's it");
            }

            return "abc";
        }
    }


}