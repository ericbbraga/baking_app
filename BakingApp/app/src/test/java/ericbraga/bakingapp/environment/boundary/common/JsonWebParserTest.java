package ericbraga.bakingapp.environment.boundary.common;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.List;

import ericbraga.bakingapp.environment.boundary.common.interfaces.Parser;
import ericbraga.bakingapp.model.Recipe;

public class JsonWebParserTest {

    @Test
    public void invalidContent_ShouldReturnNull() {
        Parser parser = new JsonWebParser();
        try {
            parser.parse(null);
            Assert.fail("Invalid content should throw an exception");
        } catch(IllegalArgumentException e) {}
    }

    @Test
    public void emptyContent_ShouldReturnEmptyList() {
        Parser parser = new JsonWebParser();
        try {
            List<Recipe> recipe = parser.parse("");
            Assert.assertEquals(0, recipe.size());
        } catch(IllegalArgumentException e) {
            Assert.fail();
        }
    }

}