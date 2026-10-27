package tw.edu.pu.csim.tcyang.basicui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
        // 1. 底層放可以捲動的數字清單
        LazyColumnExample(modifier)

        // 2. 上層疊上帶有半透明背景與手勢偵測的 ColumnExample
        ColumnExample(modifier)
    }
}

@Composable
fun LazyColumnExample(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(text = "Header")
        }
        items(50) { index ->
            Text(text = "$index")
        }
        item {
            Text(text = "Footer")
        }
    }
}

@Composable
fun ColumnExample(modifier: Modifier = Modifier) {
    // 建立一個狀態來記錄當下手勢
    var gestureText by remember { mutableStateOf("請嘗試點擊畫面") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) { // 攔截觸控與滑動手勢，讓下層無法捲動
                detectTapGestures(
                    onTap = {
                        gestureText = "短按"
                    },
                    onDoubleTap = {
                        gestureText = "連點兩下"
                    },
                    onLongPress = {
                        gestureText = "長按"
                    }
                )
            }
            .background(Color(0x8AE0BBE4)), // 半透明背景[cite: 3]
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("行動應用軟體開發：基本元件")
        Text("作者：余興宥")
        // 顯示目前偵測到的手勢
        Text(text = gestureText)
    }
}