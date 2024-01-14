package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        var tempList = new ArrayList<String>();
        var resultList = new ArrayList<String>();
        resultList.add(source);
        for (String delimiter : delimiters) {
            for (String s : resultList) {
                StringTokenizer st = new StringTokenizer(s, delimiter);
                while (st.hasMoreTokens()) {
                    tempList.add(st.nextToken().trim());
                }
            }

            resultList.clear();
            resultList.addAll(tempList);
            tempList.clear();
        }

        return resultList;
    }
}
