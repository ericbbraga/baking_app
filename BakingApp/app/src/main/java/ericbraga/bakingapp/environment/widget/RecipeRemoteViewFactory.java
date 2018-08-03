package ericbraga.bakingapp.environment.widget;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;
import ericbraga.bakingapp.environment.widget.model.IngredientWidget;

public class RecipeRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private List<IngredientWidget> mIngredientWidgets;

    RecipeRemoteViewFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mIngredientWidgets = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        updateIngredients();
    }

    private void updateIngredients() {
        Cursor cursor = mContext.getContentResolver().query(
                BakingContract.IngredientEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            mIngredientWidgets.clear();

            do {
                String name = cursor.getString(
                                cursor.getColumnIndex(BakingContract.IngredientEntry.NAME_COLUMN)
                );
                String measure = cursor.getString(
                        cursor.getColumnIndex(BakingContract.IngredientEntry.MEASURE_COLUMN)
                );
                long quantity = cursor.getLong(
                        cursor.getColumnIndex(BakingContract.IngredientEntry.QUANTITY_COLUMN)
                );
                mIngredientWidgets.add(new IngredientWidget(name, measure, quantity));

            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    @Override
    public void onDestroy() {
        mIngredientWidgets.clear();
        mContext = null;
        mIngredientWidgets = null;
    }

    @Override
    public int getCount() {
        return mIngredientWidgets.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        DecimalFormat decimal = new DecimalFormat("#.##");

        IngredientWidget ingredient = mIngredientWidgets.get(position);
        String ingredientFormatted = String.format("* %s - (%s %s)",
                ingredient.getName(),
                decimal.format(ingredient.getQuantity()),
                ingredient.getMeasure()
        );

        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.ingredient_widget_item);
        view.setTextViewText(R.id.ingredient_widget_name, ingredientFormatted);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
