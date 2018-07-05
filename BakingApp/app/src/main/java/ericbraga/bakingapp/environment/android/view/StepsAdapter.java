package ericbraga.bakingapp.environment.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsHolder> {
    private List<Step> mSteps;

    private StepsAdapterCallback mCallback;

    public interface StepsAdapterCallback {
        void onClickItem(Step step);
    }

    public StepsAdapter() {
        mSteps = new ArrayList<>();
    }

    public void setSteps(List<Step> steps) {
        mSteps.clear();
        mSteps.addAll(steps);
        notifyDataSetChanged();
    }

    public void setCallback(StepsAdapterCallback callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public StepsAdapter.StepsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.steps_element, parent, false);
        return new StepsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsHolder holder, int position) {
        Step step = mSteps.get(position);
        holder.mTitle.setText(step.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class StepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;

        StepsHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.steps_short_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mCallback != null) {
                int position = getAdapterPosition();
                Step step = mSteps.get(position);
                mCallback.onClickItem(step);
            }
        }
    }
}
