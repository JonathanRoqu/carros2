import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextMarca, editTextMotor, editTextChasis, editTextVin, editTextCombustion;
    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        editTextMarca = findViewById(R.id.editTextMarca);
        editTextMotor = findViewById(R.id.editTextMotor);
        editTextChasis = findViewById(R.id.editTextChasis);
        editTextVin = findViewById(R.id.editTextVin);
        editTextCombustion = findViewById(R.id.editTextCombustion);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarVehiculo();
            }
        });
    }

    private void guardarVehiculo() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("marca", editTextMarca.getText().toString());
        values.put("motor", editTextMotor.getText().toString());
        values.put("chasis", editTextChasis.getText().toString());
        values.put("vin", editTextVin.getText().toString());
        values.put("combustion", editTextCombustion.getText().toString());

        try {
            long newRowId = db.insert("vehiculos", null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Error al guardar el vehículo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vehículo guardado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }
}}