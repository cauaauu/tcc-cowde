// Generated by view binder compiler. Do not edit!
package com.example.tcc_cowde.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tcc_cowde.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPerfilBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout btMapa;

  @NonNull
  public final Button buttonAtualizar;

  @NonNull
  public final Button buttonMapa;

  @NonNull
  public final Button buttonNotificacao;

  @NonNull
  public final ImageView imgCirculo;

  @NonNull
  public final ImageView imgPerfil;

  @NonNull
  public final ImageView imgRetR;

  @NonNull
  public final ImageView imgRetV;

  @NonNull
  public final ImageView imgTopoo2;

  @NonNull
  public final TextView txtCon;

  @NonNull
  public final TextView txtGeral;

  @NonNull
  public final TextView txtMod;

  @NonNull
  public final TextView txtNome;

  @NonNull
  public final TextView txtNomeUsuario;

  @NonNull
  public final TextView txtOla;

  @NonNull
  public final TextView txtPg;

  @NonNull
  public final TextView txtProgresso;

  @NonNull
  public final TextView txtProgresso2;

  private ActivityPerfilBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout btMapa, @NonNull Button buttonAtualizar, @NonNull Button buttonMapa,
      @NonNull Button buttonNotificacao, @NonNull ImageView imgCirculo,
      @NonNull ImageView imgPerfil, @NonNull ImageView imgRetR, @NonNull ImageView imgRetV,
      @NonNull ImageView imgTopoo2, @NonNull TextView txtCon, @NonNull TextView txtGeral,
      @NonNull TextView txtMod, @NonNull TextView txtNome, @NonNull TextView txtNomeUsuario,
      @NonNull TextView txtOla, @NonNull TextView txtPg, @NonNull TextView txtProgresso,
      @NonNull TextView txtProgresso2) {
    this.rootView = rootView;
    this.btMapa = btMapa;
    this.buttonAtualizar = buttonAtualizar;
    this.buttonMapa = buttonMapa;
    this.buttonNotificacao = buttonNotificacao;
    this.imgCirculo = imgCirculo;
    this.imgPerfil = imgPerfil;
    this.imgRetR = imgRetR;
    this.imgRetV = imgRetV;
    this.imgTopoo2 = imgTopoo2;
    this.txtCon = txtCon;
    this.txtGeral = txtGeral;
    this.txtMod = txtMod;
    this.txtNome = txtNome;
    this.txtNomeUsuario = txtNomeUsuario;
    this.txtOla = txtOla;
    this.txtPg = txtPg;
    this.txtProgresso = txtProgresso;
    this.txtProgresso2 = txtProgresso2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPerfilBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPerfilBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_perfil, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPerfilBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btMapa;
      ConstraintLayout btMapa = ViewBindings.findChildViewById(rootView, id);
      if (btMapa == null) {
        break missingId;
      }

      id = R.id.buttonAtualizar;
      Button buttonAtualizar = ViewBindings.findChildViewById(rootView, id);
      if (buttonAtualizar == null) {
        break missingId;
      }

      id = R.id.buttonMapa;
      Button buttonMapa = ViewBindings.findChildViewById(rootView, id);
      if (buttonMapa == null) {
        break missingId;
      }

      id = R.id.buttonNotificacao;
      Button buttonNotificacao = ViewBindings.findChildViewById(rootView, id);
      if (buttonNotificacao == null) {
        break missingId;
      }

      id = R.id.imgCirculo;
      ImageView imgCirculo = ViewBindings.findChildViewById(rootView, id);
      if (imgCirculo == null) {
        break missingId;
      }

      id = R.id.imgPerfil;
      ImageView imgPerfil = ViewBindings.findChildViewById(rootView, id);
      if (imgPerfil == null) {
        break missingId;
      }

      id = R.id.imgRetR;
      ImageView imgRetR = ViewBindings.findChildViewById(rootView, id);
      if (imgRetR == null) {
        break missingId;
      }

      id = R.id.imgRetV;
      ImageView imgRetV = ViewBindings.findChildViewById(rootView, id);
      if (imgRetV == null) {
        break missingId;
      }

      id = R.id.imgTopoo2;
      ImageView imgTopoo2 = ViewBindings.findChildViewById(rootView, id);
      if (imgTopoo2 == null) {
        break missingId;
      }

      id = R.id.txtCon;
      TextView txtCon = ViewBindings.findChildViewById(rootView, id);
      if (txtCon == null) {
        break missingId;
      }

      id = R.id.txtGeral;
      TextView txtGeral = ViewBindings.findChildViewById(rootView, id);
      if (txtGeral == null) {
        break missingId;
      }

      id = R.id.txtMod;
      TextView txtMod = ViewBindings.findChildViewById(rootView, id);
      if (txtMod == null) {
        break missingId;
      }

      id = R.id.txtNome;
      TextView txtNome = ViewBindings.findChildViewById(rootView, id);
      if (txtNome == null) {
        break missingId;
      }

      id = R.id.txtNomeUsuario;
      TextView txtNomeUsuario = ViewBindings.findChildViewById(rootView, id);
      if (txtNomeUsuario == null) {
        break missingId;
      }

      id = R.id.txtOla;
      TextView txtOla = ViewBindings.findChildViewById(rootView, id);
      if (txtOla == null) {
        break missingId;
      }

      id = R.id.txtPg;
      TextView txtPg = ViewBindings.findChildViewById(rootView, id);
      if (txtPg == null) {
        break missingId;
      }

      id = R.id.txtProgresso;
      TextView txtProgresso = ViewBindings.findChildViewById(rootView, id);
      if (txtProgresso == null) {
        break missingId;
      }

      id = R.id.txtProgresso2;
      TextView txtProgresso2 = ViewBindings.findChildViewById(rootView, id);
      if (txtProgresso2 == null) {
        break missingId;
      }

      return new ActivityPerfilBinding((ConstraintLayout) rootView, btMapa, buttonAtualizar,
          buttonMapa, buttonNotificacao, imgCirculo, imgPerfil, imgRetR, imgRetV, imgTopoo2, txtCon,
          txtGeral, txtMod, txtNome, txtNomeUsuario, txtOla, txtPg, txtProgresso, txtProgresso2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
