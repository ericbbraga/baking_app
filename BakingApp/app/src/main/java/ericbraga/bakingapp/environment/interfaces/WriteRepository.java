package ericbraga.bakingapp.environment.interfaces;

import java.io.IOException;

import ericbraga.bakingapp.environment.repositories.exception.ParserException;
import ericbraga.bakingapp.model.Recipe;

public interface WriteRepository {
    long save(Recipe recipe) throws IOException, ParserException;
    void update(Recipe recipe) throws IOException, ParserException;
}
