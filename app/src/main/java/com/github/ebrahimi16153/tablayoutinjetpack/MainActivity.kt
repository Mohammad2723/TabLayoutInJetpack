package com.github.ebrahimi16153.tablayoutinjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.tablayoutinjetpack.screen.Home
import com.github.ebrahimi16153.tablayoutinjetpack.screen.Settings
import com.github.ebrahimi16153.tablayoutinjetpack.screen.Shopping
import com.github.ebrahimi16153.tablayoutinjetpack.ui.theme.TabLayoutInJetpackTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApp()

        }
    }
}

@ExperimentalPagerApi
@Composable
fun MyApp() {
    TabLayoutInJetpackTheme {
        TabLayout()
    }
}

@ExperimentalPagerApi
@Composable
fun TabLayout() {

    val pagerState = rememberPagerState(pageCount = 3)
    Column(modifier = Modifier.background(Color.White)) {

        Tabs(pagerState = pagerState)

        TabsContent(pagerState = pagerState)


    }

}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {

  HorizontalPager(state = pagerState) { page ->

      when(page){
          0 -> Home()
          1 -> Shopping()
          2 -> Settings()
      }

  }


}




// tab
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {


    val list = listOf(
        "Home" to Icons.Default.Home,
        "Shopping" to Icons.Default.ShoppingCart,
        "Settings" to Icons.Default.Settings
    )

    val scope = rememberCoroutineScope()


    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        indicator = { tabPositions ->

            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )


        }) {

        list.forEachIndexed { index, _ ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                icon = { Icon(imageVector = list[index].second, contentDescription = "") },
                text = {
                    Text(
                        text = list[index].first,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
            )
        }
    }

}
