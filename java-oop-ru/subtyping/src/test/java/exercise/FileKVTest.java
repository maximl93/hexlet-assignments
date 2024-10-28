package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void fileKVTest() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));

        assertThat(Utils.deserialize(Utils.readFile(filepath.toString()))).isEqualTo(storage.toMap());

        storage.set("key2", "value2");

        assertThat(Utils.deserialize(Utils.readFile(filepath.toString()))).isEqualTo(storage.toMap());

        storage.unset("key");

        assertThat(Utils.deserialize(Utils.readFile(filepath.toString()))).isEqualTo(storage.toMap());
    }
    // END
}
