// Generated by view binder compiler. Do not edit!
package com.example.premierprojet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.premierprojet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class WeatherActivityBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button bt;

  @NonNull
  public final ProgressBar pb;

  @NonNull
  public final TextView tv;

  @NonNull
  public final TextView tvError;

  private WeatherActivityBinding(@NonNull LinearLayout rootView, @NonNull Button bt,
      @NonNull ProgressBar pb, @NonNull TextView tv, @NonNull TextView tvError) {
    this.rootView = rootView;
    this.bt = bt;
    this.pb = pb;
    this.tv = tv;
    this.tvError = tvError;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static WeatherActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WeatherActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.weather_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WeatherActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bt;
      Button bt = ViewBindings.findChildViewById(rootView, id);
      if (bt == null) {
        break missingId;
      }

      id = R.id.pb;
      ProgressBar pb = ViewBindings.findChildViewById(rootView, id);
      if (pb == null) {
        break missingId;
      }

      id = R.id.tv;
      TextView tv = ViewBindings.findChildViewById(rootView, id);
      if (tv == null) {
        break missingId;
      }

      id = R.id.tv_error;
      TextView tvError = ViewBindings.findChildViewById(rootView, id);
      if (tvError == null) {
        break missingId;
      }

      return new WeatherActivityBinding((LinearLayout) rootView, bt, pb, tv, tvError);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
