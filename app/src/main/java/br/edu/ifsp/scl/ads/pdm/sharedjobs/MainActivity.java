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
import br.edu.ifsp.scl.ads.pdm.sharedjobs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        //Listener telefone celular
        activityMainBinding.telefoneCelularCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()) {
                    activityMainBinding.telefoneCelularLl.setVisibility(View.VISIBLE);
                } else {
                    activityMainBinding.telefoneCelularLl.setVisibility(View.GONE);
                    activityMainBinding.telefoneCelularEt.setText("");
                }
            }
        });

        activityMainBinding.formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activityMainBinding.anoFormaturaEt.setText("");
                activityMainBinding.anoConclusaoEt.setText("");
                activityMainBinding.instituicaoEt.setText("");
                activityMainBinding.tituloMonografiaEt.setText("");
                activityMainBinding.orientadorEt.setText("");
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Medio")){
                    activityMainBinding.formacaoLl.setVisibility(View.VISIBLE);
                    activityMainBinding.formacaoL2.setVisibility(View.GONE);
                    activityMainBinding.formacaoL3.setVisibility(View.GONE);
                } else if(((TextView) view).getText().equals("Graduacao") || ((TextView) view).getText().equals("Especializacao")) {
                    activityMainBinding.formacaoLl.setVisibility(View.GONE);
                    activityMainBinding.formacaoL2.setVisibility(View.VISIBLE);
                    activityMainBinding.formacaoL3.setVisibility(View.GONE);
                }
                else {
                    activityMainBinding.formacaoLl.setVisibility(View.GONE);
                    activityMainBinding.formacaoL2.setVisibility(View.VISIBLE);
                    activityMainBinding. formacaoL3.setVisibility(View.VISIBLE);
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
        activityMainBinding.nomeEt.setText("");
        activityMainBinding.emailEt.setText("");
        activityMainBinding.telefoneEt.setText("");
        activityMainBinding.telefoneCelularEt.setText("");
        activityMainBinding.masculinoRb.setChecked(true);
        activityMainBinding.anoFormaturaEt.setText("");
        activityMainBinding.anoConclusaoEt.setText("");
        activityMainBinding.instituicaoEt.setText("");
        activityMainBinding.tituloMonografiaEt.setText("");
        activityMainBinding.orientadorEt.setText("");
        activityMainBinding.telefoneCelularCb.setChecked(false);
        activityMainBinding.atualizoesEmailCb.setChecked(false);
        activityMainBinding.formacaoSp.setSelection(0);
        activityMainBinding.vagasEt.setText("");
        activityMainBinding.dataNascimentoEt.setText("");
    }

    private void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(activityMainBinding.nomeEt.getText().toString()).append("\n");
        sumarioSb.append("E-mail: ").append(activityMainBinding.emailEt.getText().toString()).append("\n");
        sumarioSb.append("Telefone: ").append(activityMainBinding.telefoneEt.getText().toString()).append("\n");

        if(activityMainBinding.telefoneCelularCb.isChecked()) {
            sumarioSb.append("Telefone celular: ").append(activityMainBinding.telefoneCelularEt.getText().toString()).append("\n");
        } else {
            sumarioSb.append("Nao há telefone celular").append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (activityMainBinding.sexoRg.getCheckedRadioButtonId()) {
            case R.id.masculinoRb:
                sumarioSb.append("masculino");
                break;
            case R.id.femininoRb:
                sumarioSb.append("feminino");
                break;
        }
        sumarioSb.append("\n");
        sumarioSb.append("Data de nascimento: ").append(activityMainBinding.dataNascimentoEt.getText().toString()).append("\n");
        sumarioSb.append("Formacao: ").append(((TextView) activityMainBinding.formacaoSp.getSelectedView()).getText()).append("\n");

        // Index formacao array
        int fundamentalIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Fundamental");
        int medioIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Medio");
        int graduacaoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Graduacao");
        int especializacaoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Especializacao");
        int mestradoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Mestrado");
        int doutoradoIndex = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Doutorado");

        if(fundamentalIndex == activityMainBinding.formacaoSp.getSelectedItemPosition() || medioIndex == activityMainBinding.formacaoSp.getSelectedItemPosition()) {
            sumarioSb.append("Ano de formatura: ").append(activityMainBinding.anoFormaturaEt.getText().toString()).append("\n");
        } else if(graduacaoIndex == activityMainBinding.formacaoSp.getSelectedItemPosition() || especializacaoIndex == activityMainBinding.formacaoSp.getSelectedItemPosition()) {
            sumarioSb.append("Ano de conclusao: ").append(activityMainBinding.anoConclusaoEt.getText().toString()).append("\n")
                    .append("Instituicao: ").append(activityMainBinding.instituicaoEt.getText().toString()).append("\n");
        } else {
            sumarioSb.append("Ano de conclusao: ").append(activityMainBinding.anoConclusaoEt.getText().toString()).append("\n")
                    .append("Instituicao: ").append(activityMainBinding.instituicaoEt.getText().toString()).append("\n")
                    .append("Titulo da Monografia: ").append(activityMainBinding.tituloMonografiaEt.getText().toString()).append("\n")
                    .append("Orientador: ").append(activityMainBinding.orientadorEt.getText().toString()).append("\n");
        }

        sumarioSb.append("Vagas de interesse: ").append(activityMainBinding.vagasEt.getText().toString()).append("\n");

        if(activityMainBinding.atualizoesEmailCb.isChecked()) {
            sumarioSb.append("Desejo receber atualizacoes por email").append("\n");
        } else {
            sumarioSb.append("Nao desejo receber atualizacoes por email").append("\n");
        }

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }
}