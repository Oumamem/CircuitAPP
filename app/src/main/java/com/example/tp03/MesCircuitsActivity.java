package com.example.tp03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MesCircuitsActivity extends AppCompatActivity {
    Cursor cur;
    int op=0;
    String x;
    SQLiteDatabase bdcircuits;
    LinearLayout layNaviguer, layRecherche, layRecherchePrix;
    EditText _txtCircuit, _txtDepart, _txtArrive, _txtPrix, _txtDuree, _txtRechercheCircuit, _prixmax, _prixmin;
    ImageButton _btnRecherche, _btnRecherche1;
    Button _btnFirst, _btnLast, _btnNext, _btnPrevious, _btnAdd, _btnDelete, _btnUpdate, _btnCancel, _btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_circuits);
        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer) ;
        _prixmin = (EditText) findViewById((R.id.prixmin));
        _prixmax = (EditText) findViewById((R.id.prixmax));
        _btnRecherche1 = (ImageButton) findViewById(R.id.btnRecherche1);
        _txtRechercheCircuit = (EditText) findViewById((R.id.txtRechercheCircuit));
        _txtCircuit = (EditText) findViewById((R.id.txtCircuit));
        _txtDepart = (EditText) findViewById((R.id.txtDepart));
        _txtArrive = (EditText) findViewById((R.id.txtArrive));
        _txtDuree = (EditText) findViewById((R.id.txtDuree));
        _txtPrix = (EditText) findViewById((R.id.txtPrix));
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnFirst = (Button) findViewById(R.id.btnFirst);
        _btnLast = (Button) findViewById(R.id.btnLast);
        _btnNext = (Button) findViewById(R.id.btnNext);
        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        //Ouvrir une connexion vers bd
        bdcircuits = openOrCreateDatabase("Circuits", MODE_PRIVATE,null);
        //Créer la table Circuits
        bdcircuits.execSQL("CREATE TABLE IF NOT EXISTS CIRCUITS (id integer primary key autoincrement, CIRCUIT VARCHAR, DEPART VARCHAR, ARRIVE VARCHAR, DUREE integer, PRIX REAL)");
        layNaviguer.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnRecherche1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cur = bdcircuits.rawQuery("select * from CIRCUITS where PRIX between ? AND ?", new String[]{_prixmin.getText().toString(),_prixmax.getText().toString()});
                try {
                    cur.moveToFirst();
                    _txtCircuit.setText(cur.getString(1));
                    _txtArrive.setText(cur.getString(2));
                    _txtDepart.setText(cur.getString(3));
                    _txtDuree.setText((cur.getString(4)));
                    _txtPrix.setText(cur.getString(5));
                    if (cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);

                    }else{
                        layNaviguer.setVisibility(View.VISIBLE);
                        _btnPrevious.setEnabled(false);
                        _btnNext.setEnabled(true);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Aucun résultat",Toast.LENGTH_SHORT).show();
                    _txtCircuit.setText("");
                    _txtPrix.setText("");
                    _txtDuree.setText("");
                    _txtDepart.setText("");
                    _txtArrive.setText("");
                    layNaviguer.setVisibility(View.VISIBLE);
                }
            }
        });

        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cur = bdcircuits.rawQuery("select * from CIRCUITS where DEPART like ?", new String[]{"%"+_txtRechercheCircuit.getText().toString()+"%"});
            try {
                cur.moveToFirst();
                _txtCircuit.setText(cur.getString(1));
                _txtArrive.setText(cur.getString(2));
                _txtDepart.setText(cur.getString(3));
                _txtDuree.setText((cur.getString(4)));
                _txtPrix.setText(cur.getString(5));
                if (cur.getCount() == 1){
                    layNaviguer.setVisibility(View.INVISIBLE);

                }else{
                    layNaviguer.setVisibility(View.VISIBLE);
                    _btnPrevious.setEnabled(false);
                    _btnNext.setEnabled(true);
                }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Aucun résultat",Toast.LENGTH_SHORT).show();
                _txtCircuit.setText("");
                _txtPrix.setText("");
                _txtDuree.setText("");
                _txtDepart.setText("");
                _txtArrive.setText("");
                layNaviguer.setVisibility(View.VISIBLE);
                }
            }
        });
        _btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToFirst();
                    _txtCircuit.setText(cur.getString(1));
                    _txtArrive.setText(cur.getString(2));
                    _txtDepart.setText(cur.getString(3));
                    _txtDuree.setText((cur.getString(4)));
                    _txtPrix.setText(cur.getString(5));
                    _btnPrevious.setEnabled(false);
                    _btnNext.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Aucun circuit n'est disponible.",Toast.LENGTH_SHORT).show();

                }
            }
        });
        _btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToLast();
                    _txtCircuit.setText(cur.getString(1));
                    _txtArrive.setText(cur.getString(2));
                    _txtDepart.setText(cur.getString(3));
                    _txtDuree.setText((cur.getString(4)));
                    _txtPrix.setText(cur.getString(5));
                    _btnPrevious.setEnabled(true);
                    _btnNext.setEnabled(false);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Aucun circuit n'est disponible.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToNext();
                    _txtCircuit.setText(cur.getString(1));
                    _txtArrive.setText(cur.getString(2));
                    _txtDepart.setText(cur.getString(3));
                    _txtDuree.setText((cur.getString(4)));
                    _txtPrix.setText(cur.getString(5));
                    _btnPrevious.setEnabled(true);
                    if (cur.isLast()){
                        _btnNext.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToPrevious();
                    _txtCircuit.setText(cur.getString(1));
                    _txtArrive.setText(cur.getString(2));
                    _txtDepart.setText(cur.getString(3));
                    _txtDuree.setText((cur.getString(4)));
                    _txtPrix.setText(cur.getString(5));
                    _btnNext.setEnabled(true);
                    if (cur.isFirst()){
                        _btnPrevious.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtCircuit.setText("");
                _txtArrive.setText("");
                _txtDepart.setText("");
                _txtDuree.setText("");
                _txtPrix.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnCancel.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                layNaviguer.setVisibility(View.INVISIBLE);
                layRecherche.setVisibility(View.INVISIBLE);
            }
        });

        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tester si les champs ne sont pas vides
                try {
                    x = cur.getString(0);
                    op = 2;

                    _btnSave.setVisibility(View.VISIBLE);
                    _btnCancel.setVisibility(View.VISIBLE);

                    _btnDelete.setVisibility(View.INVISIBLE);
                    _btnUpdate.setEnabled(false);
                    _btnAdd.setVisibility(View.INVISIBLE);

                    layNaviguer.setVisibility(View.INVISIBLE);
                    layRecherche.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Sélectionnez un circuit puis appyuer sur le bouton de modification",Toast.LENGTH_SHORT).show();
                }

            }
        });

        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1){
                    // insertion
                    bdcircuits.execSQL("insert into CIRCUITS (CIRCUIT,DEPART,ARRIVE,DUREE, PRIX) values (?,?,?,?,?);", new String[] {_txtCircuit.getText().toString(), _txtArrive.getText().toString(),_txtDepart.getText().toString(),_txtDuree.getText().toString(),_txtPrix.getText().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    bdcircuits.execSQL("update CIRCUITS set CIRCUIT=?, DEPART=?, ARRIVE=?, DUREE=?, PRIX=? where id=?;", new String[] {_txtCircuit.getText().toString(), _txtArrive.getText().toString(),_txtDepart.getText().toString(),_txtDuree.getText().toString(),_txtPrix.getText().toString(),x});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                _btnRecherche.performClick();
                layRecherche.setVisibility(View.VISIBLE);
            }
        });
        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);

                layRecherche.setVisibility(View.VISIBLE);
            }
        });


        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x=  cur.getString(0);
                    AlertDialog dial = MesOptions();
                    dial.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Selectionner le circuit à supprimer",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        }

    private AlertDialog MesOptions() {
        AlertDialog MiDia = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Vous êtes certain de supprimer ce circuit?")
                .setIcon(R.drawable.icon_valid)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bdcircuits.execSQL("delete from CIRCUITS where id=?;",new String[] {cur.getString(0)});
                        _txtCircuit.setText("");
                        _txtArrive.setText("");
                        _txtDepart.setText("");
                        _txtDuree.setText("");
                        _txtPrix.setText("");
                        layNaviguer.setVisibility(View.INVISIBLE);
                        cur.close();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        return MiDia;
    }
}
