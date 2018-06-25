package ericbraga.bakingapp.environment.common.interfaces;

import ericbraga.bakingapp.environment.common.RecipeWebCollection;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;

public interface Parser {
    RecipeWebCollection parse(String content) throws ParserException;
}
