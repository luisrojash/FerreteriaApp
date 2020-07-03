package com.example.ferreteriaapp.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import timber.log.Timber;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected abstract void setContentView();

    protected abstract Bundle getExtrasInf();

    public abstract int getLayout();

    protected B binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
        Timber.d("onCreate");
        setContentView();
        getExtrasInf();
    }

}
