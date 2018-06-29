package ericbraga.bakingapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;

import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.view.IngredientsAdapter;
import ericbraga.bakingapp.environment.android.view.StepsAdapter;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.mvpcontract.DescriptionRecipeContract;
import ericbraga.bakingapp.presenter.DescriptionPresenter;

public class DescriptionRecipeActivity extends AppCompatActivity implements
        DescriptionRecipeContract.View, StepsAdapter.StepsAdapterCallback {

    private DescriptionRecipeContract.Presenter mPresenter;

    private ImageView mImageRecipe;
    private TextView mNameRecipe;
    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mStepsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe recipe = (Recipe) getIntent().getExtras().get("Recipe");

        setContentView(R.layout.activity_description_recipe);
        mImageRecipe = findViewById(R.id.description_recipe_image);
        mNameRecipe = findViewById(R.id.description_recipe_name);
        mIngredientsRecyclerView = findViewById(R.id.description_ingredients);
        mStepsRecyclerView = findViewById(R.id.description_steps);

        mPresenter = new DescriptionPresenter(recipe);
        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showImageRecipe(Bitmap recipeBitmap) {
        mImageRecipe.setImageBitmap(recipeBitmap);
    }

    @Override
    public void showDescriptionRecipe(String recipeName) {
        mNameRecipe.setText(recipeName);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        mIngredientsRecyclerView.setAdapter(adapter);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showSteps(List<Step> steps) {
        StepsAdapter adapter = new StepsAdapter(steps);
        adapter.setCallback(this);
        mStepsRecyclerView.setAdapter(adapter);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showMoreStepInfo(Step step) {
        Intent it = new Intent(this, StepInformationActivity.class);
        it.putExtra("Step", step);
        startActivity(it);
    }

    @Override
    public void onClickItem(Step step) {
        mPresenter.onClickedItem(step);
    }
}
