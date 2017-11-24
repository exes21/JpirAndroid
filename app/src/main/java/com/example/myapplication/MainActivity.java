package com.example.myapplication;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  private Presenter presenter;

  private String email;

  @BindView(R.id.swipe_refresh_layout)
  SwipeRefreshLayout swipeRefreshLayout;

  @BindView(R.id.text_email)
  EditText emailText;

  @BindView(R.id.textView_response)
  TextView responseTextView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setupPresenter();
    setupSwipeRefreshLayout();
  }

  @OnClick(R.id.button_submit)
  public void onClickSubmitButton() {
    email = emailText.getText().toString();
    if (email.isEmpty())
      Toast.makeText(this, R.string.enter_email, Toast.LENGTH_LONG).show();
    else {
      Thread thread = new Thread(() -> {
        callHttp();
        runOnUiThread(() -> setProgressIndicator(true));
      });
      thread.start();
    }
  }

  private void setupPresenter() {
    presenter = new Presenter(this);
  }

  public void callHttp() {
    presenter.loadResponse(email, "192.168.0.183");
  }

  private void setupSwipeRefreshLayout() {
    swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipe_refresh_colors));
    swipeRefreshLayout.setOnRefreshListener(this::callHttp);
  }

  public void setProgressIndicator(final boolean active) {
    swipeRefreshLayout.post(() -> {
      if (swipeRefreshLayout != null)
        swipeRefreshLayout.setRefreshing(active);
    });
  }

  public void showResponse(String response) {
    responseTextView.setText(response);
    responseTextView.setVisibility(View.VISIBLE);
  }

  public void hideResponse() {
    responseTextView.setVisibility(View.GONE);
  }
}
