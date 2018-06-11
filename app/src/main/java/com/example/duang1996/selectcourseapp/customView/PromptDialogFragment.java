package com.example.duang1996.selectcourseapp.customview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.R;

public class PromptDialogFragment extends DialogFragment {
    private static PromptDialogFragment instance;
    private String content;

    private onNegativeClickListener mOnNegativeClickListener;

    public interface onNegativeClickListener{
        void doNegativeClick();
    }

    public static PromptDialogFragment newInstance(String alertContent) {
        if(instance == null) {
            instance = new PromptDialogFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("content", alertContent);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        content = bundle.getString("content");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.prompt_dialog_fragment, null);

        TextView alert =  view.findViewById(R.id.alertText);
        alert.setText(content);

        TextView get = view.findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                if (mOnNegativeClickListener != null) {
                    mOnNegativeClickListener.doNegativeClick();
                }
            }
        });
        return view;
    }
}