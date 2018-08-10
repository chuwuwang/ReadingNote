package material.design;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nsz.android.R;

import home.BaseAppCompatActivity;

public class DialogsActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_dialogs);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.tv_hint).setOnClickListener(this);
        findViewById(R.id.tv_edit).setOnClickListener(this);
        findViewById(R.id.tv_multi_choice).setOnClickListener(this);
        findViewById(R.id.tv_single_choice).setOnClickListener(this);
        findViewById(R.id.tv_list).setOnClickListener(this);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_hint:
                new AlertDialog.Builder(this)
                        .setTitle("Lainey")
                        .setIcon(R.drawable.ic_photo_liked)
                        .setMessage("Material Design Dialog")
                        .setNegativeButton("cancel", (dia, which) -> {
                                    dia.dismiss();
                                    Toast.makeText(this, "click cancel", Toast.LENGTH_SHORT).show();
                                }
                        )
                        .setPositiveButton("ok", null)
                        .show();
                break;
            case R.id.tv_edit:
                EditText editText = new EditText(this);
                new AlertDialog.Builder(this)
                        .setTitle("Lainey")
                        .setMessage("Material Design Dialog")
                        .setNegativeButton("cancel", null)
                        .setPositiveButton("ok", null)
                        .setView(editText, 24, 24, 24, 24)
                        .show();
                break;
            case R.id.tv_multi_choice:
                String[] data = {"AA", "BB", "CC"};
                boolean[] state = {false, true, true};
                new AlertDialog.Builder(this)
                        .setTitle("Lainey")
                        .setNegativeButton("cancel", null)
                        .setPositiveButton("ok", null)
                        .setMultiChoiceItems(data, state, (dialog1, which, isChecked) -> {
                                    Toast.makeText(this, which + ":" + isChecked, Toast.LENGTH_SHORT).show();
                                }
                        )
                        .show();
                break;
            case R.id.tv_single_choice:
                String[] single = {"AA", "BB", "CC", "CC", "CC", "CC", "CC", "CC"};
                new AlertDialog.Builder(this)
                        .setTitle("Lainey")
                        .setNegativeButton("agree", null)
                        .setSingleChoiceItems(single, 3, (dialog, which) -> {
                                    Toast.makeText(this, which + " select", Toast.LENGTH_SHORT).show();
                                }
                        )
                        .show();
                break;
            case R.id.tv_list:
                String[] ite = {"DD", "EE", "FF"};
                new AlertDialog.Builder(this)
                        .setTitle("Lainey")
                        .setIcon(R.drawable.ic_photo_liked)
                        .setNegativeButton("cancel", null)
                        .setPositiveButton("ok", null)
                        .setItems(ite, (dialog, which) -> {

                                }
                        )
                        .show();
                break;
            default:
                break;
        }
    }


}
