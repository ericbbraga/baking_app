package ericbraga.bakingapp.utils;

import java.util.List;

import ericbraga.bakingapp.model.Recipe;

public interface Parser {
    List<Recipe> parse(String content);
}
