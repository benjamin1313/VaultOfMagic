package com.example.vaultofmagic

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.vaultofmagic.data.ItemRepository
import com.example.vaultofmagic.models.DndItem
import com.example.vaultofmagic.ui.theme.VaultOfMagicTheme

class ItemListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaultOfMagicTheme {
                val materialBlue700= Color(0xFF1976D2)
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                val scope = rememberCoroutineScope()
                Scaffold(
                    topBar = { TopAppBar(title = {Text("Vault Of Magic")},
                        backgroundColor = materialBlue700,
                    )
                    },
                    drawerContent = { },
                    drawerGesturesEnabled = false,
                    content = { paddingValues ->  ItemList(paddingValues)}
                )
            }
        }
    }
}

@Composable
fun ItemList(paddingValues: PaddingValues) {
    val repo: ItemRepository = ItemRepository()
    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        var dndItems by remember { mutableStateOf<List<DndItem>?>(listOf()) }
        LaunchedEffect(repo){
            dndItems = repo.getAllItems()
        }
        LazyColumn(){
            dndItems?.forEach {  item{ ItemCard(item = it) } }
        }
    }
}


@Composable
fun ItemCard(item: DndItem){
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            val intent = Intent(context, ItemDetailsActivity::class.java)
            intent.putExtra("ITEMID", item.id)
            startActivity(context, intent, null)
                   },
    backgroundColor = Color.LightGray) {
        Column() {
            Text(text = item.name, fontSize = 35.sp, modifier = Modifier.padding(horizontal = 10.dp))
            Row() {
                Text(text = item.type, fontStyle = FontStyle.Italic, modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = item.rarity, fontWeight = FontWeight.Bold)
            }
        }
    }
}