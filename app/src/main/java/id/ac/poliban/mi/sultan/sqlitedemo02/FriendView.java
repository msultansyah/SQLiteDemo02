package id.ac.poliban.mi.sultan.sqlitedemo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.poliban.mi.sultan.sqlitedemo02.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.mi.sultan.sqlitedemo02.domain.Friend;

import static id.ac.poliban.mi.sultan.sqlitedemo02.MainActivity.EVENT_INSERT;
import static id.ac.poliban.mi.sultan.sqlitedemo02.MainActivity.EVENT_UPDATE;
import static id.ac.poliban.mi.sultan.sqlitedemo02.MainActivity.EVENT_VIEW;

public class FriendView extends AppCompatActivity {
    private FriendDaoImplSQLite ds;
    private TextView tvId;
    private EditText etId;
    private EditText etName;
    private EditText etDescription;
    private EditText etCharacter;
    private Button btSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);

        ds = new FriendDaoImplSQLite(this);

        tvId = findViewById(R.id.tvId);
        etId = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etCharacter= findViewById(R.id.etCharacter);
        etDescription= findViewById(R.id.etDescription);
        btSimpan = findViewById(R.id.btSimpan);

        btSimpan.setOnClickListener(v -> {
            if (!isValidate()) return;

            if(getIntent().getIntExtra("event", -1) == EVENT_INSERT){
                Friend friend = new Friend(
                        etName.getText().toString(),
                        etCharacter.getText().toString(),
                        etDescription.getText().toString());
                ds.insert(friend);
            }

            else if(getIntent().getIntExtra("event", -1)==EVENT_UPDATE) {
                Friend friend = new Friend(
                        Integer.parseInt(etId.getText().toString()),
                        etName.getText().toString(),
                        etCharacter.getText().toString(),
                        etDescription.getText().toString());
                ds.update(friend);
            }
            onBackPressed();
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().getExtras() !=null && getSupportActionBar() !=null)
            if (getIntent().getIntExtra("event", -1) == EVENT_INSERT){
                setTitle("Tambah Data Karakter");
                settingTampilan(EVENT_INSERT);
            }
            else if (getIntent().getIntExtra("event", -1) == EVENT_UPDATE) {
                setTitle("Update Data Karakter");
                settingTampilan(EVENT_UPDATE);
                tampilkanData(getIntent().getIntExtra("id", -1));
            }
            else if (getIntent().getIntExtra("event", -1) == EVENT_VIEW) {
                setTitle("Detail Karakter");
                settingTampilan(EVENT_VIEW);
                tampilkanData(getIntent().getIntExtra("id", -1));
            }
    }

    private void settingTampilan(int event) {
        switch (event){
            case EVENT_INSERT :
                tvId.setVisibility(View.GONE); //lenyap
                etId.setVisibility(View.GONE);
                etName.setEnabled(true); //bisa diedit
                etCharacter.setEnabled(true);
                etDescription.setEnabled(true);
                btSimpan.setVisibility(View.VISIBLE); //muncul
                break;
            case EVENT_UPDATE :
                tvId.setVisibility(View.VISIBLE); //muncul
                etId.setVisibility(View.VISIBLE);
                etId.setEnabled(false);
                etName.setEnabled(true); //bisa diedit
                etCharacter.setEnabled(true);
                etDescription.setEnabled(true);
                btSimpan.setVisibility(View.VISIBLE); //muncul
                break;
            case EVENT_VIEW :
                tvId.setVisibility(View.VISIBLE); //muncul
                etId.setVisibility(View.VISIBLE);
                etId.setEnabled(false); //tidak bisa diedit
                etName.setEnabled(false);
                etCharacter.setEnabled(false);
                etDescription.setEnabled(false);
                btSimpan.setVisibility(View.GONE); //lenyap
                break;
        }
    }

    private void tampilkanData(int id) {
        Friend friend = ds.getFriendById(id);
        etId.setText(String.valueOf(friend.getId()));
        etName.setText(friend.getName());
        etCharacter.setText(friend.getCharacter());
        etDescription.setText(friend.getDescription());
    }
    private boolean isValidate() {
        EditText[] ets = new EditText[]{
                etName, etCharacter
        };
//jika etName, dan etEmail kosong maka munculkan error
        for (EditText et : ets) {
            if (et.getText().toString().isEmpty()) {
                et.setError("field harus diisi!");
                return false;
            }
        }
        return true;
    }

}
