// Generated by view binder compiler. Do not edit!
package com.example.note_takingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.note_takingapp.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemNoteBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final AppCompatImageButton btnDelete;

  @NonNull
  public final TextView tvBody;

  @NonNull
  public final TextView tvTitle;

  private ItemNoteBinding(@NonNull MaterialCardView rootView,
      @NonNull AppCompatImageButton btnDelete, @NonNull TextView tvBody,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.tvBody = tvBody;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemNoteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemNoteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_note, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemNoteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDelete;
      AppCompatImageButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.tvBody;
      TextView tvBody = ViewBindings.findChildViewById(rootView, id);
      if (tvBody == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ItemNoteBinding((MaterialCardView) rootView, btnDelete, tvBody, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
