import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun Date.toFrenchDate() : String {
    val formatter= SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}