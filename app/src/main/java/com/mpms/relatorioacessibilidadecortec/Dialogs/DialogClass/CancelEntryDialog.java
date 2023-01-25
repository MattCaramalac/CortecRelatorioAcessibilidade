package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class CancelEntryDialog extends DialogFragment implements TagInterface {

    DialogListener listener;

    public CancelEntryDialog() {

    }

    public void setListener (DialogListener listener) {
        this.listener = listener;
    }

    public static CancelEntryDialog newInstance() {
        return new CancelEntryDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(requireActivity());
        aBuilder.setTitle("AVISO")
                .setMessage("Você deseja interromper este cadastro? Todos os dados inseridos serão excluídos")
                .setPositiveButton("Interromper", (dialog, which) -> {
                    listener.onPositiveClick();
                    if (dialog != null)
                        dialog.dismiss();
                }).setNegativeButton("Cancelar", (dialog, which) -> {
                    if (dialog != null)
                        dialog.dismiss();
                });
        return aBuilder.create();
    }

    public interface DialogListener {
        void onPositiveClick();
    }
}
