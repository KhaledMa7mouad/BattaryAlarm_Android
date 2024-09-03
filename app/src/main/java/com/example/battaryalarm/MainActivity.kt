package com.example.battaryalarm

import android.content.Context
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.battaryalarm.ui.theme.BattaryAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BattaryAlarmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    BattaryAlarmApp()
                }
            }
        }
    }
}

@Composable
fun BattaryAlarmApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val battaryLevel = remember { mutableStateOf(getBatteryLevel(context)) }
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        if (getBatteryLevel(context)<=20)
        Image(painter = painterResource(id = R.drawable.battery_low), contentDescription = "Battary High" )
        else
            Image(painter = painterResource(id = R.drawable.battery_full), contentDescription = "Battary High" )
    }
}

@Preview(showBackground = true)
@Composable
fun BattaryAlarmAppPreview() {
    BattaryAlarmTheme {
        BattaryAlarmApp()
    }
}

fun getBatteryLevel(context: Context): Int {
    val batteryManger = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return batteryManger.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}