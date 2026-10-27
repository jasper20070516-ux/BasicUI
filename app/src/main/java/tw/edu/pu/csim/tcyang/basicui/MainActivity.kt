package tw.edu.pu.csim.tcyang.basicui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    Box {
        LazyColumnExample(modifier)
    }
}

@Composable
fun LazyColumnExample(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0x8AE0BBE4)), // 設定 16 進位背景顏色[cite: 3]
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 放入投影片 7 的文字內容
        item {
            Text(text = "行動應用軟體開發：基本元件")
        }
        item {
            Text(text = "作者：余興宥")
        }

        // Add a single item (Header)
        item {
            Text(text = "Header")
        }

        // Add 50 items (數字 0 到 49)
        items(50) { index ->
            Text(text = "$index")
        }

        // Add another single item (Footer)
        item {
            Text(text = "Footer")
        }
    }
}