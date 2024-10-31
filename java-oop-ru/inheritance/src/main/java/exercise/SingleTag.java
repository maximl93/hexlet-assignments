package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> tagAttributes) {
        super(tagName, tagAttributes);
    }

    @Override
    public String toString() {
        if (tagAttributes.isEmpty()) {
            return String.format("<%s>", tagName);
        }

        return tagTextFormat();
    }

    public String tagTextFormat() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("<%s ", tagName));


        List<String> attributes = new ArrayList<>();
        tagAttributes.forEach((key, value) -> attributes.add(String.format("%s=\"%s\"", key, value)));

        result.append(String.join(" ", attributes));

        result.append(">");

        return result.toString();
    }
}
// END
