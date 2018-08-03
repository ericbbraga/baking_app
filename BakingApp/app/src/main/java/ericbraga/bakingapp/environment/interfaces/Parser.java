package ericbraga.bakingapp.environment.interfaces;

import ericbraga.bakingapp.environment.repositories.web.RecipeWebCollection;
import ericbraga.bakingapp.environment.repositories.exception.ParserException;

public interface Parser<T> {
    T parse(String content) throws ParserException;
    String toJson(T object) throws ParserException;
}
