package com.example.kampusgo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kampusgo.ui.theme.KampusGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val activity = this
        setContent {
            KampusGoTheme {
                var showEmpty by remember { mutableStateOf(false) }
                val events = if (showEmpty) emptyList() else sampleEvents
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        EventList(
                            events = events,
                            onEventClick = { id ->
                                val intent = Intent(activity, EventDetailActivity::class.java)
                                intent.putExtra("id", id)
                                activity.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f),
                        )
                        Button(
                            onClick = { showEmpty = !showEmpty },
                            modifier = Modifier.padding(horizontal = 24.dp)
                        ) {
                            Text(if (showEmpty) "Show events" else "Show empty")
                        }
                        Button(
                            onClick = {
                                activity.startActivity(Intent(activity, ProfileXmlActivity::class.java))
                            },
                            modifier = Modifier.padding(horizontal = 24.dp)
                        ) {
                            Text("XML form")
                        }
                    }
                }
            }
        }
    }
}
