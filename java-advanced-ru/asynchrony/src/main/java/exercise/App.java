package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String path1, String path2, String path3) {
        CompletableFuture<String> firstFile = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(getFullPath(path1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> secondFile = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(getFullPath(path2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> outcome = firstFile.thenCombine(secondFile, (first, second) -> {
                    return first + " " + second;
                })
                .exceptionally(ex -> {
                    System.out.println("Oops! We have an exception - " + ex.getMessage());
                    return null;
                });

        return outcome;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/result.txt");
        // END
    }
}

