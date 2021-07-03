package com.example.myapplication2.ui.testing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication2.R;
import org.jetbrains.annotations.NotNull;

public class testingFragment extends Fragment {
  TextView textView;
  Button btn1;
  Button btn2;


  public View onCreateView(@NotNull LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_testing, container, false);
    textView = root.findViewById(R.id.text_testing);
    btn1 = root.findViewById(R.id.button);
    btn2 = root.findViewById(R.id.button2);

    btn1.setOnClickListener(v -> textView.setText("1"));
    btn2.setOnClickListener(v -> textView.setText("2"));

    return root;
  }
}
