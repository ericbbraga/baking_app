package ericbraga.bakingapp.environment.repositories.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;
import ericbraga.bakingapp.environment.repositories.local.database.DatabaseHelper;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private static final String DB_NAME = "baking.test.db";
    private static final int VERSION = 1;

    @Before
    public void setUp() {
        dropAllTables();
    }

    @Test
    public void check_If_All_Tables_Are_Created() {
        Context context = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = new DatabaseHelper(context, DB_NAME, VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        verifyTableExists(db, BakingContract.RecipeEntry.TABLE_NAME);
        verifyTableExists(db, BakingContract.IngredientEntry.TABLE_NAME);
        verifyTableExists(db, BakingContract.StepEntry.TABLE_NAME);

        verifyTableExists(db, BakingContract.RecipeIngredientEntry.TABLE_NAME);
        verifyTableExists(db, BakingContract.RecipeStepEntry.TABLE_NAME);
    }

    private void verifyTableExists(SQLiteDatabase database, String tableName) {
        try {
            Cursor cursor = database.query(
                    tableName,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            Assert.assertEquals(0, cursor.getCount());
        } catch (SQLiteException e) {
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void tearDown() {
        dropAllTables();
    }

    private void dropAllTables() {
        Context context = InstrumentationRegistry.getTargetContext();
        context.deleteDatabase(DB_NAME);
    }
}