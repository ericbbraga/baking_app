package ericbraga.bakingapp.environment.injectors.modules;

import android.content.Context;
import android.content.Intent;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

@Module
public class MainRouterModule {

    private final Context mContext;

    public MainRouterModule(Context context) {
        mContext = context;
    }

    @Provides
    public DisplayRecipesContract.Router provideRouter() {
        return new DisplayRecipesContract.Router() {
            @Override
            public void displayNextScreen(Recipe recipe) {
                Intent intent = new Intent(mContext, DescriptionRecipeActivity.class);
                intent.putExtra("recipe", recipe);
                mContext.startActivity(intent);
            }
        };
    }
}
