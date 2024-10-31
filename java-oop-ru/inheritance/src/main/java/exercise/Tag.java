package exercise;

import java.util.Map;

// BEGIN
public class Tag {

    public String tagName;
    public Map<String, String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }
}
// END
