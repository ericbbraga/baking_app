package ericbraga.bakingapp.environment.repositories.local.models.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;

public class RecipeIngredientsSteps {
    @Embedded
    public RecipeEntity recipeEntry;

    @Relation(parentColumn = BakingContract.RecipeEntry._ID, entityColumn = "recipe_id")
    public List<IngredientEntity> ingredients;

    @Relation(parentColumn = BakingContract.RecipeEntry._ID, entityColumn = "recipe_id")
    public List<StepEntity> steps;
}
