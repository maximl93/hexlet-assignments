package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> validateFail = new ArrayList<>();

        Field[] fields = address.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && obj == null) {
                    validateFail.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return validateFail;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> validateFail = new HashMap<>();

        Field[] fields = address.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && obj == null) {
                    validateFail.put(field.getName(), List.of("cannot be null"));
                }

                if (field.isAnnotationPresent(MinLength.class) && obj.toString().length() < 4) {
                    List<String> temp = validateFail.getOrDefault(field.getName(), new ArrayList<>());
                    temp.add("length less than 4");
                    validateFail.put(field.getName(), temp);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return validateFail;
    }
}
// END
