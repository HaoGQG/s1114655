package com.example.s1114655

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.s1114655.ui.theme.S1114655Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1114655Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){

    Column(modifier = Modifier
        .fillMaxSize()
    ){
        Animation()
    }

}
@Composable
fun SecondScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Text(
            text = "主要機構",
            color = Color.Red
        )
        //twobutton()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    var showMenu by remember { mutableStateOf(false) }
    Column {
        TopAppBar(
            title = { Image(painter = painterResource(id = R.drawable.maria), contentDescription = "maria") },

            actions = {
                IconButton(
                    onClick = { showMenu = true  }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpFirst")})

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpSecond")})
                }

            }

        )

        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}
@Composable
fun Animation(){
    var appear by remember { mutableStateOf(true) }  //背景出現
    var h by remember { mutableStateOf("瑪利亞基金會服務總覽") }
    Column {
       Text(text = h, color = Color.Blue)
        AnimatedVisibility(
            visible = appear,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 1500)),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 1500))

        ) {
            Image(
                painter = painterResource(id = R.drawable.service),
                contentDescription = "service"
            )
        }
        AnimatedVisibility(
            visible = !appear,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 5000)),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 5000))

        ) {
            Image(
                painter = painterResource(id = R.drawable.me),
                contentDescription = "me"
            )
        }
        Button(
            onClick = {
                appear = !appear
                if(!appear){
                    h = "關於App作者"
                }else{
                    h = "瑪利亞基金會服務總覽"
                }
            }
        ) {
            if (!appear) Text(text = "服務總覽")
            else Text(text = "作者：您的系級與姓名")
        }
    }
}

@Composable
fun twobutton(){
    var appear by remember { mutableStateOf(true) }
    var h1 by remember { mutableStateOf("「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。\n") }
    var h2 by remember { mutableStateOf("長按以下圖片，可以觀看愛心家園地圖\n") }
    Column {
        Row {
            Button(
                onClick = {
                        h1 = "「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。\n"
                        h2 = "長按以下圖片，可以觀看愛心家園地圖\n"
                }
            ) {
                Text(text = "台中市愛心家園")
            }
            Button(
                onClick = {
                        h1 = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。\n"
                        h2 = "雙擊以下圖片，可以觀看瑪利亞學園地圖\n"
                }
            ) {
                Text(text = "瑪利亞學園")
            }
        }
        Text(text = h1)
        Text(text = h2, color = Color.Blue)
    }
}
