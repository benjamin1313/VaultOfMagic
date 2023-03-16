package com.example.vaultofmagic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vaultofmagic.data.ItemRepository
import com.example.vaultofmagic.models.DndItem
import com.example.vaultofmagic.ui.theme.VaultOfMagicTheme

class ItemDetailsActivity : ComponentActivity() {
    private val repo = ItemRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaultOfMagicTheme {
                val materialBlue700= Color(0xFF1976D2)
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                val scope = rememberCoroutineScope()
                val id = intent.getIntExtra("ITEMID",0 )
                val item = repo.getItemById(id)
                Scaffold(
                    topBar = { TopAppBar(title = {
                        if (item != null) {
                            Text(item.name)
                        }
                    },
                        backgroundColor = materialBlue700,
                        navigationIcon = {
                            IconButton(onClick = {finish() },
                            ) {Icon(Icons.Default.ArrowBack, contentDescription = "Go back")}

                        }
                    )
                    },
                    drawerContent = { },
                    drawerGesturesEnabled = false,
                    content = { paddingValues ->  DetailsPage(item, paddingValues)}
                )
            }
        }
    }
}

@Composable
fun DetailsPage(item:DndItem?, paddingValues: PaddingValues) {
    if (item == null){
        Text(text = "Item not found")
    }else {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())) {
            AsyncImage(
                model = item.imgUrl,
                contentDescription = null,
                modifier = Modifier.size(300.dp).padding(10.dp),
            )
            Text(text = item.name, fontSize = 30.sp)
            Row() {
                Text(text = item.type, fontStyle = FontStyle.Italic, modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = item.rarity, fontWeight = FontWeight.Bold)
            }
            Card(modifier = Modifier.padding(10.dp)) {
                Text(text = item.description)
            }
        }
    }
}