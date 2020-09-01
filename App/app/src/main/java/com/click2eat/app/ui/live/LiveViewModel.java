package com.click2eat.app.ui.live;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is live fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}