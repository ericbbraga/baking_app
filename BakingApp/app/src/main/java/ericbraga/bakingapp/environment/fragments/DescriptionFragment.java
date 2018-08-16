package ericbraga.bakingapp.environment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.view.IngredientsAdapter;
import ericbraga.bakingapp.environment.view.StepsAdapter;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionFragment extends Fragment
        implements DescriptionRecipeContract.View, StepsAdapter.StepsAdapterCallback {

    private DescriptionRecipeContract.Presenter mPresenter;

    private TextView mNameRecipe;
    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mStepsRecycleView;
    private Context mContext;

    public void setPresenter(DescriptionRecipeContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.description_recipe_fragment, container,
                false);

        configureViews(root);

        return root;
    }

    private void configureViews(View root) {
        mNameRecipe = root.findViewById(R.id.description_recipe_name);
        mIngredientsRecyclerView = root.findViewById(R.id.description_ingredients);
        mStepsRecycleView = root.findViewById(R.id.description_steps_preview);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showDescriptionRecipe(String recipeName) {
        mNameRecipe.setText(recipeName);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        mIngredientsRecyclerView.setAdapter(adapter);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void showSteps(List<Step> steps, int currentStepIndex) {
        StepsAdapter adapter = new StepsAdapter(steps, currentStepIndex);
        adapter.setCallback(this);
        mStepsRecycleView.setAdapter(adapter);
        mStepsRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void onClickItem(int position) {
        if (mPresenter != null) {
            mPresenter.showMoreSteps(position);
        }
    }
}
