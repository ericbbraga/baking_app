package ericbraga.bakingapp.environment.injectors.components;

import dagger.Component;
import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.environment.injectors.modules.description.DescriptionModule;

@Component(modules = {DescriptionModule.class})
public interface DescriptionInjector {
    void inject(DescriptionRecipeActivity activity);
}
