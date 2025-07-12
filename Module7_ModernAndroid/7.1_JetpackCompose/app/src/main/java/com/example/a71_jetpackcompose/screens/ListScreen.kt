package com.example.a71_jetpackcompose.screens


/**Created by Raviteja Emandi on 12,July,2025 **/
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController) {
    val names = listOf("Alice", "Bob", "Charlie", "David", "Ella", "Frank", "Grace")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Name List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(names) { name ->
                Text(text = "• $name", modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("counter") }) {
            Text("Back to Counter")
        }
    }
}