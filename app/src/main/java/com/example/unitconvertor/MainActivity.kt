package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import androidx.compose.material3.MaterialTheme as MaterialTheme1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Convert( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConvertorTheme {
        Convert()
    }
}

@Composable
fun Convert(modifier: Modifier= Modifier) {
//    column is used to write the ui element one below another
    var inputvalue by remember{ mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by  remember { mutableStateOf("SELECT") }
    var outputunit by remember{ mutableStateOf(" SELECT ") }
    var iexpanded by remember { mutableStateOf(false)}
    var oexpanded by remember { mutableStateOf(false)}
    var iconversion by remember { mutableStateOf(1) }
    var oconversion by remember { mutableStateOf(100) }
    var submit by remember {mutableStateOf(false)}
    var decor= TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 25.sp,
        color = Color.Cyan

    )

    fun conversionfun(){

        val inputvalued= inputvalue.toDoubleOrNull() ?: 0.0
        val result= (inputvalued*iconversion)/oconversion
        outputvalue=result.toString()
    }

    Column( modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text("Unit Converter",
            modifier = Modifier.padding(16.dp),
            style= MaterialTheme1.typography.headlineLarge,
            color = Color.Green)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = { inputvalue = it },
            label = { Text("enter value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iexpanded = true }) {
                    Text(inputunit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "dropdown arrow in use"
                    )

                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        iexpanded = false
                        iconversion = 1
                        inputunit = "centimeter"
                        submit= false

                    })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        iexpanded = false
                        iconversion = 100
                        inputunit = "meter"
                        submit= false

                    })
                    DropdownMenuItem(text = { Text(text = "kilometer") }, onClick = {
                        iexpanded = false
                        iconversion = 100000
                        inputunit = "kilometer"
                        submit=false
                    })


                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oexpanded = true }) {
                    Text(text = outputunit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "dropdown arrow in use"
                    )

                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        oexpanded = false
                        oconversion = 1
                        outputunit = "centimeter"
                        submit=false
                    })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        oexpanded = false
                        oconversion = 100
                        outputunit = "meter"
                        submit=false
                    })
                    DropdownMenuItem(text = { Text(text = "kilometer") }, onClick = {
                        oexpanded = false
                        oconversion = 100000
                        outputunit = "kilometer"
                        submit= false
                    })

                }
            }
        }
        val context = LocalContext.current
        Button(onClick = {
            if (inputunit == "SELECT" || outputunit == "SELECT") {
                Toast.makeText(context, "please select vlaues", Toast.LENGTH_LONG).show()
            } else {
                submit=true
                conversionfun()
            }

        }){
            Text(text = "CONVERT")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(submit){
        Text("RESULT: ${outputvalue} $outputunit ",
            modifier = Modifier.padding(16.dp),
            style= decor
        )}
    }
}










