package ericbraga.bakingapp.environment.boundary.common;

import ericbraga.bakingapp.environment.boundary.common.interfaces.Parser;

public class ParserFactory {
    public static final int JSON = 0;

    public Parser getParser(int parserFactoryType) {
        if (parserFactoryType == JSON) {
            return new JsonWebParser();

        } else {
            throw new IllegalArgumentException("Invalid parser type");
        }
    }

}
