package io.github.ztnozdormu.common.utils;

import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;

import java.util.LinkedHashMap;

public final class ParameterChecker {

    private ParameterChecker() {
    }

    public static void checkParameter(LinkedHashMap<String, Object> parameters, String parameter, Class t) {
        checkRequiredParameter(parameters, parameter);
        checkParameterType(parameters.get(parameter), t, parameter);
    }

    public static void checkRequiredParameter(LinkedHashMap<String, Object> parameters, String parameter) {
        if (!parameters.containsKey(parameter)) {
            throw new ExchangeConnectorException(String.format("\"%s\" is a mandatory parameter!", parameter));
        }
    }

    public static void checkParameterType(Object parameter, Class t, String name) {
        if (!t.isInstance(parameter)) {
            throw new ExchangeConnectorException(String.format("\"%s\" must be of %s type.", name, t));
        } else if (t == String.class && parameter.toString().trim().equals("")) {
            throw new ExchangeConnectorException(String.format("\"%s\" must not be empty.", name));
        }
    }
}
