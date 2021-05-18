package com.example.renttools.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.renttools.model.Tool;

import java.util.ArrayList;
import java.util.List;

public class ToolsActivityViewModel {

    private MutableLiveData<List<Tool>> mTools;

    public LiveData<List<Tool>> getTools() {
        return mTools;
    }




}
