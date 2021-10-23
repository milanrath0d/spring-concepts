package org.milan.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorting params pojo
 *
 * @author Milan Rathod
 */
public class SortParams {

    private final List<String> sortParamList;

    public SortParams(String commaSeparatedString) {
        sortParamList = Arrays.stream(commaSeparatedString.split(","))
            .map(String::valueOf)
            .collect(Collectors.toList());
    }

    public List<String> getSortParamList() {
        return sortParamList;
    }
}
