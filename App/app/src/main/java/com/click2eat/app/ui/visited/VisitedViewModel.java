package com.click2eat.app.ui.visited;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisitedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisitedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is visited fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}