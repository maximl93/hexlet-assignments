package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {

    public String tagBody;
    public List<Tag> children;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String tagBody, List<Tag> children) {
        super(tagName, tagAttributes);
        this.tagBody = tagBody;
        this.children = children;
    }

    @Override
    public String toString() {
        if (children.isEmpty()) {
            return withoutChildren();
        }

        return withChildren();
    }

    private String withoutChildren() {
        if (tagAttributes.isEmpty()) {
            return String.format("<%s>%s</%s>", tagName, tagBody, tagName);
        }

        StringBuilder result = new StringBuilder();

        addToResult(result);

        result.append(tagBody);

        result.append(String.format("</%s>", tagName));

        return result.toString();
    }

    private String withChildren() {
        StringBuilder result = new StringBuilder();

        if (tagAttributes.isEmpty()) {
            result.append(String.format("<%s>", tagName));
            children.forEach(child -> result.append(child.toString()));
            result.append(tagBody);
            result.append(String.format("</%s>", tagName));
            return result.toString();
        }

        addToResult(result);

        children.forEach(child -> result.append(child.toString()));

        result.append(tagBody);
        result.append(String.format("</%s>", tagName));

        return result.toString();
    }

    private void addToResult(StringBuilder result) {
        result.append(String.format("<%s ", tagName));

        List<String> attributes = new ArrayList<>();
        tagAttributes.forEach((key, value) -> attributes.add(String.format("%s=\"%s\"", key, value)));

        result.append(String.join(" ", attributes));

        result.append(">");
    }
}
// END
