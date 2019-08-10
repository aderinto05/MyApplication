package com.myapplication.login.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.login.LoginActivity;
import com.myapplication.model.User;
import com.myapplication.util.PreferenceManagerUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Dihardja Software Solutions on 3/7/17.
 */

public class LoginMainFragment extends Fragment {
    @BindView(R.id.email_edittext)
    EditText mEmail;
    @BindView(R.id.password_edittext)
    EditText password;
    @BindView(R.id.submit_button)
    Button submitButton;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    submitButton.setEnabled(false);
                    submitButton.setClickable(false);
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkValidation(charSequence, true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidation(editable, true);
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    submitButton.setEnabled(false);
                    submitButton.setClickable(false);
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkValidation(charSequence, false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidation(editable, false);
            }
        });

        return view;
    }

    private boolean checkValidation(CharSequence charSequence, boolean isEmail){
        if (charSequence.length() == 0) {
            enableButton(false);
        } else if (!isValidEmail(charSequence) && isEmail) {
            enableButton(false);
        } else if (mEmail.getText().toString().length() == 0) {
            enableButton(false);
        } else if (password.getText().toString().length() == 0) {
            enableButton(false);
        } else {
            enableButton(true);
        }
        return true;
    }

    private boolean checkValidation(Editable editable, boolean isEmail){
        if (editable.length() == 0) {
           enableButton(false);
        } else if (!isValidEmail(editable) && isEmail) {
            enableButton(false);
        } else if (mEmail.getText().toString().length() == 0) {
            enableButton(false);
        } else if (password.getText().toString().length() == 0) {
            enableButton(false);
        } else {
            enableButton(true);
        }
        return true;
    }

    private boolean enableButton(boolean isEnable){
        if(isEnable){
            submitButton.setAlpha(1.0f);
            submitButton.setEnabled(true);
            submitButton.setClickable(true);
            return true;
        }else{
            submitButton.setAlpha(0.15f);
            submitButton.setEnabled(false);
            submitButton.setClickable(false);
            return false;
        }
    }

    @OnClick(R.id.submit_button)
    public void submitTapped() {
        if(mEmail.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.validation_email_required),Toast.LENGTH_SHORT).show();
        }else if( password.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.validation_password_required),Toast.LENGTH_SHORT).show();
        }else if(!isValidEmail(mEmail.getText().toString())){
            Toast.makeText(getActivity(), getResources().getString(R.string.validation_email_invalid),Toast.LENGTH_SHORT).show();
        }else{
            ((LoginActivity) getActivity()).openMainActivity();
            User user = new User();
            user.setName("Ade Rinto Sudarmaji");
            user.setGender("Male");
            user.setPhone("+6281511365124");
            user.setEmail(mEmail.getText().toString());
            PreferenceManagerUtil.getInstance().saveCurrentUser(user);
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}