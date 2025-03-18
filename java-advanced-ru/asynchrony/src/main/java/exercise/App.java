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

        System.out.println("reading first file");
        CompletableFuture<String> firstFile = CompletableFuture.supplyAsync(() -> {
            String content = "";
            try {
                content = Files.readString(getFullPath(path1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        System.out.println("reading second file");
        CompletableFuture<String> secondFile = CompletableFuture.supplyAsync(() -> {
            String content = "";
            try {
                content = Files.readString(getFullPath(path2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        System.out.println("writing result");
        return firstFile.thenCombine(secondFile, (first, second) -> {
            String result = first + second;
            try {
                Files.writeString(getFullPath(path3), result, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "ok";
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/result.txt");
        result.get();
        // END
    }
}

