package ericbraga.bakingapp.environment.common.interfaces;

import ericbraga.bakingapp.environment.common.WebRecipeCollection;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;

public interface Parser {
    WebRecipeCollection parse(String content) throws ParserException;
}
