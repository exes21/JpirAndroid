package com.example.myapplication.presenter;

import com.example.myapplication.DefaultSubscriber;
import com.example.myapplication.MainActivity;
import com.example.myapplication.WifiManagerUtils;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.ApiBuilder;
import com.google.gson.JsonObject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Presenter {

  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  private MainActivity activity;

  public Presenter(MainActivity activity) {
    this.activity = activity;
  }

  public void loadResponse(String email, String ipAddress) {
    activity.setProgressIndicator(true);
    //activity.hideResponse();
    WifiManagerUtils x = new WifiManagerUtils();

    String ping = x.getPing(ipAddress);
    int signLevel = x.getSignLevel(activity);
    int linkSpeed = x.getLinkSpeed(activity);

    ApiService apiService = ApiBuilder.getInstance();

    addSubscription(apiService.getData(email, ping, signLevel, linkSpeed)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber()));
  }

  private void addSubscription(Subscription subscription) {
    this.compositeSubscription.add(subscription);
  }

  private class Subscriber extends DefaultSubscriber<JsonObject> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
     // activity.setProgressIndicator(false);
    //  activity.showResponse(e.getMessage());
    }

    @Override
    public void onNext(JsonObject data) {
   //   activity.setProgressIndicator(false);
    //  activity.showResponse(data.toString());
    }
  }
}
