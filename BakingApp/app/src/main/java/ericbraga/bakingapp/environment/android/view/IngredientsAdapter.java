package ericbraga.bakingapp.environment.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {

    private List<Ingredient> mIngredients;

    public IngredientsAdapter(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_element, parent,false);

        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder ingredientsHolder, int position) {
        DecimalFormat decimal = new DecimalFormat("#.##");

        Ingredient ingredient = mIngredients.get(position);
        ingredientsHolder.mName.setText(ingredient.getName());
        ingredientsHolder.mQuantity.setText(decimal.format(ingredient.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (mIngredients != null) {
            return mIngredients.size();
        }

        return 0;
    }

    class IngredientsHolder extends RecyclerView.ViewHolder {

        TextView mQuantity;
        TextView mName;

        IngredientsHolder(View itemView) {
            super(itemView);
            mQuantity = itemView.findViewById(R.id.ingredient_quantity);
            mName = itemView.findViewById(R.id.ingredient_name);
        }
    }
}
