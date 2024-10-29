package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private String tagValue;
    private TagInterface tagInterface;

    public LabelTag(String tagValue, TagInterface tagInterface) {
        this.tagValue = tagValue;
        this.tagInterface = tagInterface;
    }

    @Override
    public String render() {
        return "<label>" + tagValue + tagInterface.render() + "</label>";
    }
}
// END
