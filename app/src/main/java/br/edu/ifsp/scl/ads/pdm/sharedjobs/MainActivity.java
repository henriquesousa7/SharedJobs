package br.edu.ifsp.scl.ads.pdm.sharedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import br.edu.ifsp.scl.ads.pdm.sharedjobs.R;

public class MainActivity extends AppCompatActivity {
    // Objetos de binding com as Views
    private EditText nomeEt;
    private EditText emailEt;
    private EditText telefoneEt;
    private RadioGroup telefoneRg;
    private RadioButton comercialRb;
    private CheckBox telefoneCelularCb;
    private LinearLayout telefoneCelularL1;
    private EditText telefoneCelular;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;
    private EditText dataNascimento;
    private Spinner formacaoSp;
    private LinearLayout formacaoLl;
    private LinearLayout formacaoL2;
    private LinearLayout formacaoL3;
    private EditText anoFormatura;
    private EditText anoConclusao;
    private EditText instituicao;
    private EditText tituloMonografia;
    private EditText orientador;
    private EditText vagas;
    private CheckBox atualizacaoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando (binding) objetos com as Views
        bindViews();

        //Listener telefone celular
        telefoneCelularCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()) {
                    telefoneCelularL1.setVisibility(View.VISIBLE);
                } else {
                    telefoneCelularL1.setVisibility(View.GONE);
                    telefoneCelular.setText("");
                }
            }
        });

        formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                anoFormatura.setText("");
                anoConclusao.setText("");
                instituicao.setText("");
                tituloMonografia.setText("");
                orientador.setText("");
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Medio")){
                    formacaoLl.setVisibility(View.VISIBLE);
                    formacaoL2.setVisibility(View.GONE);
                    formacaoL3.setVisibility(View.GONE);
                } else if(((TextView) view).getText().equals("Graduacao") || ((TextView) view).getText().equals("Especializacao")) {
                    formacaoLl.setVisibility(View.GONE);
                    formacaoL2.setVisibility(View.VISIBLE);
                    formacaoL3.setVisibility(View.GONE);
                }
                else {
                    formacaoLl.setVisibility(View.GONE);
                    formacaoL2.setVisibility(View.VISIBLE);
                    formacaoL3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickButton(View view){
        switch(view.getId()) {
            case R.id.salvarBt:
                saveForm();
                break;
            case R.id.limparBt:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void cleanForm() {
        nomeEt.setText("");
        emailEt.setText("");
        telefoneEt.setText("");
        telefoneCelular.setText("");
        masculinoRb.setChecked(true);
        anoFormatura.setText("");
        anoConclusao.setText("");
        instituicao.setText("");
        tituloMonografia.setText("");
        orientador.setText("");
        telefoneCelularCb.setChecked(false);
        atualizacaoEmail.setChecked(false);
        formacaoSp.setSelection(0);
        vagas.setText("");
        dataNascimento.setText("");
    }

    private void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(nomeEt.getText().toString()).append("\n");
        sumarioSb.append("E-mail: ").append(emailEt.getText().toString()).append("\n");
        sumarioSb.append("Telefone: ").append(telefoneEt.getText().toString()).append("\n");

        if(telefoneCelularCb.isChecked()) {
            sumarioSb.append("Telefone celular: ").append(telefoneCelular.getText().toString()).append("\n");
        } else {
            sumarioSb.append("Nao há telefone celular").append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (sexoRg.getCheckedRadioButtonId()) {
            case R.id.masculinoRb:
                sumarioSb.append("masculino");
                break;
            case R.id.femininoRb:
                sumarioSb.append("feminino");
                break;
        }
        sumarioSb.append("\n");
        sumarioSb.append("Data de nascimento: ").append(dataNascimento.getText().toString()).append("\n");
        sumarioSb.append("Formacao: ").append(((TextView) formacaoSp.getSelectedView()).getText()).append("\n");

        // Index formacao array
        int fundamentalIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Fundamental");
        int medioIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Medio");
        int graduacaoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Graduacao");
        int especializacaoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Especializacao");
        int mestradoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Mestrado");
        int doutoradoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Doutorado");

        if(fundamentalIndex == formacaoSp.getSelectedItemPosition() || medioIndex == formacaoSp.getSelectedItemPosition()) {
            sumarioSb.append("Ano de formatura: ").append(anoFormatura.getText().toString()).append("\n");
        } else if(graduacaoIndex == formacaoSp.getSelectedItemPosition() || especializacaoIndex == formacaoSp.getSelectedItemPosition()) {
            sumarioSb.append("Ano de conclusao: ").append(anoConclusao.getText().toString()).append("\n")
                    .append("Instituicao: ").append(instituicao.getText().toString()).append("\n");
        } else {
            sumarioSb.append("Ano de conclusao: ").append(anoConclusao.getText().toString()).append("\n")
                    .append("Instituicao: ").append(instituicao.getText().toString()).append("\n")
                    .append("Titulo da Monografia: ").append(tituloMonografia.getText().toString()).append("\n")
                    .append("Orientador: ").append(orientador.getText().toString()).append("\n");
        }

        sumarioSb.append("Vagas de interesse: ").append(vagas.getText().toString()).append("\n");

        if(atualizacaoEmail.isChecked()) {
            sumarioSb.append("Desejo receber atualizacoes por email").append("\n");
        } else {
            sumarioSb.append("Nao desejo receber atualizacoes por email").append("\n");
        }

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void bindViews() {
        nomeEt = findViewById(R.id.nomeEt);
        emailEt = findViewById(R.id.emailEt);
        telefoneEt = findViewById(R.id.telefoneEt);
        telefoneRg = findViewById(R.id.telefoneRg);
        comercialRb = findViewById(R.id.comercialRb);
        telefoneCelularCb = findViewById(R.id.telefoneCelularCb);
        telefoneCelularL1 = findViewById(R.id.telefoneCelularLl);
        telefoneCelular = findViewById(R.id.telefoneCelularEt);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
        dataNascimento = findViewById(R.id.dataNascimentoEt);
        formacaoSp = findViewById(R.id.formacaoSp);
        formacaoLl = findViewById(R.id.formacaoLl);
        formacaoL2 = findViewById(R.id.formacaoL2);
        formacaoL3 = findViewById(R.id.formacaoL3);
        anoFormatura = findViewById(R.id.anoFormaturaEt);
        anoConclusao = findViewById(R.id.anoConclusaoEt);
        instituicao = findViewById(R.id.instituicaoEt);
        tituloMonografia = findViewById(R.id.tituloMonografiaEt);
        orientador = findViewById(R.id.orientadorEt);
        vagas = findViewById(R.id.vagasEt);
        atualizacaoEmail = findViewById(R.id.atualizoesEmailCb);
    }

}