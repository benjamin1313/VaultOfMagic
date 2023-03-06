package com.example.vaultofmagic

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaultofmagic.ui.theme.VaultOfMagicTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    VaultOfMagicTheme {
        val materialBlue700= Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopAppBar(title = {Text("Vault Of Magic")},
                    backgroundColor = materialBlue700,
                    navigationIcon = {
                        IconButton(onClick = {scope.launch { scaffoldState.drawerState.open() } },
                            ) {Icon(Icons.Default.Menu, contentDescription = "Menu")}

                    }
            )},
            drawerContent = { MenuDrawer() },
            content = { paddingValues ->  PageContent(paddingValues)}
        )
    }
}


@Composable
fun MenuItem(menuItem: MenuItemModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable { menuItem.onClick }) {

        Row() {
            Icon(imageVector = menuItem.icon, menuItem.description, modifier = Modifier.padding(5.dp))
            Text(text = menuItem.title, modifier = Modifier.padding(5.dp))
        }
    }

}

@Composable
fun MenuDrawer(){
    Column() {
        MenuItem(menuItem = MenuItemModel("1", "Home", Icons.Default.Home, "Home screen") { openNewPage() })
        MenuItem(menuItem = MenuItemModel("2", "Magic Items", Icons.Default.Star, "List of magic items.") { openNewPage() })
        MenuItem(menuItem = MenuItemModel("3", "Create Item", Icons.Default.Edit, "Create a new magic item.") { openNewPage() })
    }
}


@Composable
fun PageContent(pagePadding:PaddingValues){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(pagePadding)){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.padding(10.dp)){
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Welcome To", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    Text(text = "The Vault of Magic", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
            Box(modifier = Modifier.padding(10.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Here in the vault you will find a collection of various magical items and artifacts. \nOpen the menu to browse the vaults collection or add your own items to the vault.")
                }
            }
        }

    }
}


fun openNewPage() {
    TODO("Not yet implemented")
}


data class MenuItemModel(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)

