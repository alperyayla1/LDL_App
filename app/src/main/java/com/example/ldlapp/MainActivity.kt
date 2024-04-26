package com.example.ldlapp
import androidx.compose.ui.text.font.FontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {
    var ldlValue by remember { mutableStateOf("") }
    var tcValue by remember { mutableStateOf("") }
    var hdlValue by remember { mutableStateOf("") }
    val tglValue by remember { mutableStateOf("") }
    var result1 by remember { mutableStateOf("") }
    var result2 by remember { mutableStateOf("") }
    var result3 by remember { mutableStateOf("") }

    val tcFocusRequester = remember { FocusRequester() }
    val hdlFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = ldlValue,
            onValueChange = { ldlValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(
                "Total Cholesterol",
                fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                tcFocusRequester.requestFocus()
            })
        )

        OutlinedTextField(
            value = tcValue,
            onValueChange = { tcValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(tcFocusRequester),
            label = { Text(
                "Triglycerides",
                fontWeight = FontWeight.Bold)},
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                hdlFocusRequester.requestFocus()
            })
        )

        OutlinedTextField(
            value = hdlValue,
            onValueChange = { hdlValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(hdlFocusRequester),
            label = { Text(
                "HDL Cholesterol",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp))},
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                hdlFocusRequester.requestFocus()
            })

        )

        Button(
            onClick = {
                // Perform calculations here
                val ldl = ldlValue.toIntOrNull() ?: 0
                val tc = tcValue.toIntOrNull() ?: 0
                val hdl = hdlValue.toIntOrNull() ?: 0
                val tgl = tglValue.toIntOrNull() ?: 0

                // Calculate result 1 (adding all values)
                result1 = (ldl + tc + hdl + tgl).toString()

                // Calculate result 2 (different operation)
                // Modify this according to your operation
                result2 = (ldl).toString()

                // Calculate result 3 (different operation)
                // Modify this according to your operation
                result3 = (tc).toString()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Calculate", fontSize = 20.sp)
        }

    }

        Column {
            Text(
                "Yayla           LDL-C: $result1",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp,start = 8.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Friedewald LDL-C: $result2",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp,start = 8.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Sampson    LDL-C: $result3",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp,start = 8.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Calculator() // Preview your Calculator composable
}
