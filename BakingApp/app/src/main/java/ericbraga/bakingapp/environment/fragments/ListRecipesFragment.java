package ericbraga.bakingapp.environment.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.factories.RecipePresenterFactory;
import ericbraga.bakingapp.environment.repositories.image.GlideLoader;
import ericbraga.bakingapp.environment.view.RecipesCardAdapter;
import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

public class ListRecipesFragment extends Fragment implements DisplayRecipesContract.View,
        RecipesCardAdapter.RecipesCardItemCallback {

    private DisplayRecipesContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private View mNoElementsView;
    private RecipesCardAdapter mRecipesCardAdapter;
    private Looper mLooper;
    private Context mContext;

    public ListRecipesFragment() {}

    public void setPresenter(DisplayRecipesContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.list_recipes_fragment, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        initRecipeAdapter();

        mRecyclerView = root.findViewById(R.id.baking_list);
        mRecyclerView.setAdapter(mRecipesCardAdapter);

        mNoElementsView = root.findViewById(R.id.baking_no_elements);
    }

    private void initRecipeAdapter() {
        ImageRepository<Drawable> imageRepository = new GlideLoader(mContext);
        RecipePresenterFactory<Drawable> factoryPresenter =
                new RecipePresenterFactory<>(imageRepository);

        mRecipesCardAdapter = new RecipesCardAdapter(factoryPresenter);
        mRecipesCardAdapter.setRecipeCallback(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mLooper = mContext.getMainLooper();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void display(final RecipeCollection recipes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setVisibility(View.VISIBLE);
                mNoElementsView.setVisibility(View.INVISIBLE);
                mRecipesCardAdapter.setRecipeCollection(recipes);
            }
        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showEmptyList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNoElementsView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void hideEmptyList() {
        mNoElementsView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateRecipeStatus(final Recipe recipe) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecipesCardAdapter.updateRecipeOnCollection(recipe);
            }
        });
    }

    private void runOnUiThread(Runnable runnable) {
        if (mLooper != null) {
            new Handler(mLooper).post(runnable);
        }
    }

    @Override
    public void onItemClick(Recipe recipe) {
        mPresenter.recipeChosen(recipe);
    }

    @Override
    public void favoriteItem(Recipe recipe, boolean starred) {
        mPresenter.favoriteItem(recipe, starred);
    }

}
