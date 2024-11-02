package org.ifinalframework.xml;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author iimik
 * @version 1.2.4
 **/
@Setter
@Getter
@RequiredArgsConstructor
public class Element {
    private final String name;
    private final Map<String, String> attributes = new LinkedHashMap<>();
    private final List<Element> elements = new LinkedList<>();

    private String value;

    public void addElement(Element element) {
        elements.add(element);
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("<").append(name);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            builder.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }


        builder.append(">");

        if (Objects.nonNull(value)) {
            builder.append(value);
        }


        builder.append("</").append(name).append(">");


        return builder.toString();

    }
}
