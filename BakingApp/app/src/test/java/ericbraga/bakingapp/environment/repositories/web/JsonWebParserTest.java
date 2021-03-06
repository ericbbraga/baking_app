package ericbraga.bakingapp.environment.repositories.web;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.environment.interfaces.Parser;
import ericbraga.bakingapp.environment.repositories.exception.ParserException;
import ericbraga.bakingapp.environment.repositories.web.models.RecipeWeb;

public class JsonWebParserTest {

    @Test
    public void nullableContent_ShouldThrowAnException() {
        JsonWebParser parser = new JsonWebParser();
        try {
            parser.parse(null);
            Assert.fail("Null content should throw an exception");
        } catch(ParserException e) {}
    }

    @Test
    public void emptyContent_ShouldReturnEmptyList() {
        JsonWebParser parser = new JsonWebParser();
        try {
            RecipeWebCollection recipes = parser.parse("");
            Assert.assertEquals(0, recipes.size());
        } catch(ParserException e) {
            Assert.fail();
        }
    }

    @Test
    public void invalidContent_ShouldThrowAnException() {
        Parser parser = new JsonWebParser();
        Path path = Paths.get("src/test/java/ericbraga/resources/invalid_content.json");

        List<String> lines = new ArrayList<>();

        try {
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();

        for (String line: lines) {
            sb.append(line);
        }

        try {
            parser.parse(sb.toString());
            Assert.fail("Invalid content should throw an exception");
        } catch(ParserException e) {}
    }

    @Test
    public void incompleteContent_ShouldThrowAnException() {
        JsonWebParser parser = new JsonWebParser();
        Path path = Paths.get("src/test/java/ericbraga/resources/incomplete_content.json");

        List<String> lines = new ArrayList<>();

        try {
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();

        for (String line: lines) {
            sb.append(line);
        }

        try {
            RecipeWebCollection recipes = parser.parse(sb.toString());
            if (recipesValid(recipes)) {
                Assert.fail();
            }
        } catch(ParserException e) {
            Assert.fail();
        }
    }

    private boolean recipesValid(RecipeWebCollection recipeWebCollection) {
        boolean valid = true;
        List<RecipeWeb> recipes = recipeWebCollection.getRecipes();

        for (RecipeWeb recipe : recipes) {
            if (recipe.getName() == null ||
                recipe.getStepWebs() == null ||
                recipe.getIngredientWeb() == null) {
                valid = false;
                break;
            }
        }

        return valid;
    }

}