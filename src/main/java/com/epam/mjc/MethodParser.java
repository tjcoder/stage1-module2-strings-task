package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        var delimiters = new ArrayList<String>();
        delimiters.add("(");
        delimiters.add(")");
        delimiters.add(",");
        delimiters.add(" ");

        StringSplitter splitter = new StringSplitter();
        var resultList = splitter.splitByDelimiters(signatureString, delimiters);

        if (!List.of("private", "public", "protected").contains(resultList.get(0))) {
            resultList.add(0, null);
        }

        var methodSignature = new MethodSignature(resultList.get(2));
        methodSignature.setAccessModifier(resultList.get(0));
        methodSignature.setReturnType(resultList.get(1));

        var arguments = methodSignature.getArguments();
        for (int i = 3; i < resultList.size(); i += 2) {
            var type = resultList.get(i);
            var name = resultList.get(i + 1);
            arguments.add(new MethodSignature.Argument(type, name));
        }

        return methodSignature;
    }
}
