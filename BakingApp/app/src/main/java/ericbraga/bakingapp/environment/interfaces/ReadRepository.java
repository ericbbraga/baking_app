package ericbraga.bakingapp.environment.interfaces;

import java.io.IOException;

import ericbraga.bakingapp.environment.repositories.exception.ParserException;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface ReadRepository {
    RecipeCollection listRecipes() throws IOException, ParserException;
    Recipe getStarredRecipe() throws IOException, ParserException;
}
