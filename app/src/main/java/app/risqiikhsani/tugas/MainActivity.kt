package app.risqiikhsani.tugas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.risqiikhsani.tugas.ui.theme.TugasTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Cat Lover !")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
        Text(text = "Created by :")
        Text(text = "Risqi Ikhsani")
        Text(text = "195410259")
    }
}


@Composable
fun CatCard(item: Item) {
    val expanded = remember { mutableStateOf(false) }
//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    // ganti ke animateDpAsState untuk animation
    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
        // animationSpec untuk customize animation
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
//                .padding(bottom = extraPadding)
                // untuk animation
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))

            ){
                Text(text = "Kucing imut ${item.title}!")
                if (expanded.value) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "nama : ${item.title}")
                    Text(text = "umur : ${item.age}")
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = null, // Provide a proper description
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .size(240.dp)
                            .padding(vertical = 10.dp)
                    )
                    Text(modifier = Modifier.padding(vertical = 10.dp),text = item.text,)
                }
            }
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "sembunyikan" else "tampilkan")
            }
        }
    }
}



@Composable
fun MyApp(modifier: Modifier = Modifier) {

    //    var shouldShowOnboarding by remember { mutableStateOf(true) }
    // ganti dengan rememberSaveable agar saat rotate, activity not lost
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            CatCards()
        }
    }
}

// object item
data class Item(
    val image: Int,
    val title: String,
    val age: Int,
    val text: String
)

// data kucing
@Composable
fun generateItems(): List<Item> {
    val item1 = Item(image = R.drawable.cat1, title = "Garfield",age=1, text = "Garfield, Sang Petualang Pemalas: Garfield adalah kucing yang suka bersantai dan makan makanan lezat. Dia adalah teman yang cocok untuk Anda yang suka beristirahat dan menikmati hidup dengan santai.")
    val item2 = Item(image = R.drawable.cat2, title = "Bella",age=1, text = "Bella, Si Kucing yang Penuh Kasih: Bella adalah kucing yang penuh dengan kasih sayang. Dia akan memberikan banyak cinta dan pelukan kepada Anda setiap hari.")
    val item3 = Item(image = R.drawable.cat3, title = "Oreo",age=1, text = "Oreo, Si Kucing Dua Warna: Oreo memiliki bulu yang kontras dan mencolok seperti kue Oreo. Dia adalah kucing yang selalu menarik perhatian di mana pun dia berada.")
    val item4 = Item(image = R.drawable.cat4, title = "Gizmo",age=3, text = "Gizmo, Sang Kucing Teknologi: Gizmo suka bermain dengan perangkat elektronik dan selalu ingin tahu tentang segala sesuatu. Dia adalah kucing yang cerdas dan penasaran.")
    val item5 = Item(image = R.drawable.cat5, title = "Cici",age=3, text = "Cici, Si Kucing Kecil yang Cerdik: Cici adalah kucing kecil yang cerdik dan penuh energi. Dia selalu mencari cara baru untuk bersenang-senang dan bermain.")
    val item6 = Item(image = R.drawable.cat6, title = "Rocky",age=4, text = "Rocky, Si Kucing Pemberani: Rocky adalah kucing yang penuh keberanian dan tidak takut dengan apapun. Dia adalah teman yang setia dan siap melindungi Anda.")
    val item7 = Item(image = R.drawable.cat7, title = "Mika",age=5, text = "Mika, Si Kucing Nakal: Mika adalah kucing yang selalu sibuk dengan tingkah nakalnya. Anda tak akan pernah tahu apa saja kejutan yang dia siapkan dengan keisengannya.")
    val item8 = Item(image = R.drawable.cat8, title = "Luna",age=4, text = "Luna, Sang Kucing Elegan: Luna adalah simbol keanggunan dan kecantikan. Keberadaannya yang anggun dan bulu halusnya membuatnya menjadi teman yang sempurna untuk sentuhan kesopanan.")
    val item9 = Item(image = R.drawable.cat9, title = "Simba",age=4, text = "Simba, Sang Penjelajah Pemberani: Simba adalah petualang yang tak kenal takut, siap menjelajahi petualangan menarik di rumah Anda. Dia akan membuat setiap hari terasa seperti safari.")
    val item10 = Item(image = R.drawable.cat10, title = "Poy",age=3, text = "Poy,si Manis: Kucing memiliki suara yang merdu yang akan membuat hati Anda tersentuh. Suaranya yang manis akan membuat Anda merasa bahagia.")

    return listOf(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10)
}

@Composable
private fun CatCards(
    modifier: Modifier = Modifier,
//    names: List<String> = listOf("World", "Compose")
//    names: List<String> = List(1000) { "$it" }
    items: List<Item> = generateItems()
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        // membuat list catcard dengan passing data kucing
        items(items = items) { item ->
            CatCard(item = item)
        }
    }
}


//@Composable
//private fun MyApp(modifier: Modifier = Modifier,
//                  names: List<String> = listOf("World", "Compose")
//) {
//    Column(modifier = modifier.padding(vertical = 4.dp)) {
//        for(name in names){
//            Greeting(name = name)
//        }
//    }
//}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun GreetingPreview() {
    TugasTheme {
        CatCards()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    TugasTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    TugasTheme {
        OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
    }
}
