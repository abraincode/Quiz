package app.dhimasnb.quiz;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //casting pertanyaan pertama
    private RadioGroup rdg_pertanyaan1;
    private RadioButton rd_jawaban_pertanyaan1;
    //casting pertanyaan kedua
    private EditText edt_pertanyaan2_jawaban;
    //casting pertanyaan ketiga
    private CheckBox chk1_pertanyaan3, chk2_pertanyaan3, chk3_pertanyaan3;

    private Button btn_submit;

    int isBenar1, isBenar2, isBenar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiObjek();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                koreksiJawaban();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_informasi:
                showAlert("Informasi", "Permainan Quiz.\n" +
                        "Setiap soal memiliki 30 poin, Jika anda benar menjawab semuanya, Anda akan mendapatkan 90 poin.\n" +
                        "\n" + "by. @oranganteng.");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inisialisasiObjek() {
        rdg_pertanyaan1 = (RadioGroup) findViewById(R.id.rdg_pertanyaan1);
        edt_pertanyaan2_jawaban = (EditText) findViewById(R.id.edt_pertanyaan2_jawaban);
        chk1_pertanyaan3 = (CheckBox) findViewById(R.id.chk1_pertanyaan3);
        chk2_pertanyaan3 = (CheckBox) findViewById(R.id.chk2_pertanyaan3);
        chk3_pertanyaan3 = (CheckBox) findViewById(R.id.chk3_pertanyaan3);

        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    private void koreksiJawaban() {
        int jawabanPertama = rdg_pertanyaan1.getCheckedRadioButtonId();
        String jawabanKedua = edt_pertanyaan2_jawaban.getText().toString();
        CheckBox[] chkJawabanKetiga = {
                chk1_pertanyaan3,
                chk2_pertanyaan3,
                chk3_pertanyaan3
        };

        //pertanyaan pertama
        if (jawabanPertama > -1) {
            rd_jawaban_pertanyaan1 = (RadioButton) findViewById(jawabanPertama);
            //cek jawaban pertama
            if (rd_jawaban_pertanyaan1.getText().equals("Benar")) {
                isBenar1 = 0;
            } else {
                isBenar1 = 30;
            }

            //pertanyaan kedua
            if (!jawabanKedua.isEmpty()) {
                if (jawabanKedua.equalsIgnoreCase("undang undang dasar")) {
                    isBenar2 = 30;
                } else {
                    isBenar2 = 0;
                }

                //pertanyaa ketiga
                if (chkJawabanKetiga[0].isChecked() || chkJawabanKetiga[1].isChecked() || chkJawabanKetiga[2].isChecked()) {
                    if (chkJawabanKetiga[0].isChecked() && chkJawabanKetiga[2].isChecked()) {
                        isBenar3 = 30;
                    } else if (chkJawabanKetiga[0].isChecked()) {
                        isBenar3 = 15;
                    } else if (chkJawabanKetiga[2].isChecked()) {
                        isBenar3 = 15;
                    } else {
                        isBenar3 = 0;
                    }

                    showAlert("HASIL", String.valueOf(isBenar1 + isBenar2 + isBenar3));

                } else {
                    showToast("Anda belum menjawab pertanyaan ketiga.");
                }
            } else {
                showToast("Anda belum menjawab pertanyaan kedua.");
            }
        } else {
            showToast("Anda belum menjawab pertanyaan pertama.");
        }
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String title, String text) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alertDialog.show();
    }
}
