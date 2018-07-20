package ericbraga.bakingapp.environment.common.interfaces;

import ericbraga.bakingapp.environment.common.repositories.web.RecipeWebCollection;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;

public interface Parser<T> {
    T parse(String content) throws ParserException;
    String toJson(T object) throws ParserException;
}
