package com.rijatru.development.nativappstest.presentation.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rijatru.development.nativappstest.R;
import com.rijatru.development.nativappstest.databinding.AutoCompleteFormTextBinding;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AutoCompleteFormTextFieldMvvm;
import com.rijatru.development.nativappstest.presentation.views.adapters.TextWatcherAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class AutoCompleteFormTextField extends BaseAppView implements AutoCompleteFormTextFieldMvvm.View {

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
            getComponent().inject(this);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(inflater, R.layout.auto_complete_form_text, this, true);
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
        binding.textField.addTextChangedListener(textChangedListener);

        binding.textField.setOnEditorActionListener((v, actionId, event) -> {
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
        binding.textField.removeTextChangedListener(textChangedListener);
        textChangedListener = null;
        super.onDetachedFromWindow();
    }

    @Override
    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        binding.textField.setThreshold(1);
        binding.textField.setAdapter(adapter);
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
}
