package com.rijatru.development.nativappstest.presentation.viewModels;

import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AutoCompleteFormTextFieldMvvm;
import com.rijatru.development.nativappstest.presentation.views.adapters.CustomAutoCompleteAdapter;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

public class AutoCompleteFormTextFieldViewModel extends BaseViewModel implements AutoCompleteFormTextFieldMvvm.ViewModel {

    private AutoCompleteFormTextFieldMvvm.View view;
    private String value;
    private String hint;
    private CustomAutoCompleteAdapter autoCompleteAdapter;

    public AutoCompleteFormTextFieldViewModel(AppView appView) {
        super(appView);
        this.view = (AutoCompleteFormTextFieldMvvm.View) appView;
        getComponent().inject(this);
        initAutoCompleteAdapter();
    }

    private void initAutoCompleteAdapter() {
        autoCompleteAdapter = new CustomAutoCompleteAdapter(view.getContext(), android.R.layout.select_dialog_item, new String[]{});
        this.view.setAutoCompleteAdapter(autoCompleteAdapter);
    }

    public void setAutoCompleteData(String[] data) {
        autoCompleteAdapter.setData(data);
    }

    @Override
    public String getValue() {
        if (value == null) {
            value = "";
        }
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }
}
