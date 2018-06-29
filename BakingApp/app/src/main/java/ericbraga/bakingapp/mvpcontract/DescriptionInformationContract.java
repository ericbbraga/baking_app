package ericbraga.bakingapp.mvpcontract;

public interface DescriptionInformationContract {
    interface View {
        void showProgress();
        void hideProgress();
        void setRecipeTitle(String title);
        void loadRecipeBitmapFrom(String url);
        void showMoreInformation();
    }

    interface Presenter {
        void attach(View view);
        void detach();
        void viewClicked();
    }
}
