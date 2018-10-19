package com.rijatru.development.nativappstest.presentation.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rijatru.development.nativappstest.AppImplementation;
import com.rijatru.development.nativappstest.R;
import com.rijatru.development.nativappstest.databinding.AutoCompleteFormTextBinding;
import com.rijatru.development.nativappstest.dependencyInjection.component.DaggerViewsComponent;
import com.rijatru.development.nativappstest.dependencyInjection.component.ViewsComponent;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AutoCompleteFormTextFieldMvvm;
import com.rijatru.development.nativappstest.presentation.views.adapters.TextWatcherAdapter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class AutoCompleteFormTextField extends AppCompatAutoCompleteTextView implements AutoCompleteFormTextFieldMvvm.View {

    public static final String FIELD_TYPE_NUMBER = "number";
    public static final String FIELD_TYPE_EMAIL = "email";
    public static final String FIELD_TYPE_PASSWORD = "password";
    private String fieldType = "";
    private String hint;
    private boolean editable = true;
    private boolean isMandatory = false;
    private boolean mandatoryAlertOn = false;

    protected AutoCompleteFormTextBinding binding;
    private AutoCompleteFormTextFieldMvvm.ViewModel viewModel;
    private TextWatcher textChangedListener;
    private Subject<String> subject = PublishSubject.create();

    @Inject
    ViewModelsFactory viewModelFactory;

    @Inject
    Bus bus;

    public AutoCompleteFormTextField(Context context) {
        super(context);
        init(context);
    }

    public AutoCompleteFormTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public AutoCompleteFormTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormTextField);
            if (typedArray != null) {
                final int arraySize = typedArray.getIndexCount();

                for (int i = 0; i < arraySize; ++i) {

                    int attr = typedArray.getIndex(i);

                    switch (attr) {
                        case R.styleable.FormTextField_hint:
                            hint = typedArray.getString(attr);
                            break;
                        case R.styleable.FormTextField_fieldType:
                            fieldType = typedArray.getString(attr);
                            break;
                        case R.styleable.FormTextField_editable:
                            editable = typedArray.getBoolean(attr, true);
                            break;
                        case R.styleable.FormTextField_mandatory:
                            isMandatory = typedArray.getBoolean(attr, true);
                            break;
                    }
                }
                typedArray.recycle();
            }
        }
    }

    protected void init(Context context) {
        if (!isInEditMode()) {

            ViewsComponent component = DaggerViewsComponent.builder().appComponent(AppImplementation.getApp().getAppComponent()).build();
            component.inject(this);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = AutoCompleteFormTextBinding.inflate(inflater);

            viewModel = (AutoCompleteFormTextFieldMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL);
            viewModel.setHint(hint);
            binding.setViewModel(viewModel);
            initListeners();
            initSearchSubject();
        }
    }

    private void initListeners() {
        textChangedListener = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() >= 0) {
                    searchAddress(editable.toString());
                }
            }
        };
        addTextChangedListener(textChangedListener);

        setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard();
                return true;
            }
            return false;
        });
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
        setUpFieldType();
    }

    private void setUpFieldType() {
        switch (fieldType) {
            case FIELD_TYPE_NUMBER:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                break;
            case FIELD_TYPE_EMAIL:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case FIELD_TYPE_PASSWORD:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                break;
        }
    }

    @Override
    public String getValue() {
        return viewModel.getValue();
    }

    @Override
    public void setValue(String value) {
        viewModel.setValue(value);
        binding.setViewModel(viewModel);
    }

    public TextView getEditText() {
        return binding.textField;
    }

    @Override
    protected void onDetachedFromWindow() {
        removeTextChangedListener(textChangedListener);
        textChangedListener = null;
        super.onDetachedFromWindow();
    }

    @Override
    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        setThreshold(1);
        setAdapter(adapter);
    }

    public void setData(String[] municipalitiesArray) {
        viewModel.setAutoCompleteData(municipalitiesArray);
    }

    public void searchAddress(String string) {
        subject.onNext(string);
    }

    @SuppressLint("CheckResult")
    private void initSearchSubject() {
        subject.debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(viewModel::searchMovies);
    }

    public void hideKeyboard() {
        //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        //View view = getContext().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        //if (view == null) {
        //   view = new View(getActivity());
        // }
        //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void filter() {
        performFiltering("", 0);
    }
}
