package ericbraga.bakingapp.environment.injectors.modules.description;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.presenter.DescriptionPresenter;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

@Module
public class DescriptionModule {
    @Provides
    public DescriptionRecipeContract.Presenter providePresenter() {
        return new DescriptionPresenter();
    }
}
