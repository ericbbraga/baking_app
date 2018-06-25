package ericbraga.bakingapp.boundary;

import java.util.Iterator;

import ericbraga.bakingapp.environment.common.repositories.web.models.RecipeWeb;

public interface RecipeCollection {
    boolean recipesValid();
    Iterator<RecipeWeb> iterator();
    int size();
}
