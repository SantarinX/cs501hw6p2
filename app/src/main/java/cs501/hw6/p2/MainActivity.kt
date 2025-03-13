package cs501.hw6.p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Slider
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableFloatStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PolygonAndPolyline()
        }
    }
}


@Composable
fun PolygonAndPolyline() {
    val initLocation = LatLng(42.44161845167356,-71.06247011572123)
    val parkPoints = listOf(
        LatLng(42.43605417501554,-71.0661568120122),
        LatLng(42.43642336057751,-71.06441874057055),
        LatLng(42.43598489061944,-71.06261026114225),
        LatLng(42.43660918975261,-71.06220960617065),
        LatLng(42.436596817654234,-71.06207214295864),
        LatLng(42.43731959154576,-71.06120511889458),
        LatLng(42.437175581801725,-71.06123697012663),
        LatLng(42.44052830013875,-71.05600666254759),
        LatLng(42.44113276008471,-71.05745907872915),
        LatLng(42.44405674964571,-71.05602610856295),
        LatLng(42.447631511361365,-71.05810951441526),
        LatLng(42.44738955524257,-71.05923805385828),
        LatLng(42.44738955524257,-71.05923805385828),
        LatLng(42.44719064583149,-71.06025159358978),
        LatLng(42.44726857683272,-71.06174927204847),
        LatLng(42.44658896575815,-71.06285501271486),
        LatLng(42.44626115654286,-71.0637042671442),
        LatLng(42.4456109759071,-71.06472350656986),
        LatLng(42.445186672500405,-71.06795825064182),
        LatLng(42.441713214290445,-71.06805983930826),
        LatLng(42.43916447060412,-71.0670007020235),
        LatLng(42.43781595249165,-71.06709491461515),
        LatLng(42.43781273580825,-71.06700975447893),
        LatLng(42.437291630916896,-71.06708753854036),
        LatLng(42.437291630916896,-71.06708753854036),
        LatLng(42.43719686161291,-71.06678143143654),
        LatLng(42.43720403736159,-71.06678143143654),
        LatLng(42.43643993923469,-71.0671167075634),
        LatLng(42.436332054163074,-71.06708653271198),
        LatLng(42.436518873378155,-71.06608372181654),
        LatLng(42.4360388334773,-71.0661480948329),
    )
    val polylinePoints = remember { listOf(
        LatLng(42.43955516428293,-71.06715425848961),
        LatLng(42.439594258259845,-71.06694370508194),
        LatLng(42.43968259076345,-71.06678310781717),
        LatLng(42.440158643904994,-71.06642939150333),
        LatLng(42.44026281097602,-71.06625739485025),
        LatLng(42.44029175998877,-71.06617156416178),
        LatLng(42.44024227448879,-71.06561802327633),
        LatLng(42.44027617206048,-71.06551140546799),
        LatLng(42.44049267071722,-71.06525391340256),
        LatLng(42.44055749645519,-71.0652019456029),
        LatLng(42.4408897891659,-71.06513421982527),
        LatLng(42.4410070685254,-71.06507956981659),
        LatLng(42.44107634736879,-71.06500882655382),
        LatLng(42.441150327192005,-71.0648750513792),
        LatLng(42.44128467828639,-71.06460750102997),
        LatLng(42.441432389930576,-71.06436006724834),
        LatLng(42.44152418375848,-71.06428127735853),
        LatLng(42.4415694620661,-71.06425780802965),
        LatLng(42.441643193885,-71.06424137949944),
        LatLng(42.4417646780014,-71.06424674391747),
        LatLng(42.44230454957563,-71.06447741389275),
        LatLng(42.442687306301586,-71.06458805501461),
        LatLng(42.44271402743366,-71.06467757374048),
        LatLng(42.442767222246,-71.0647164657712),
        LatLng(42.44286000378732,-71.06472250074148),
        LatLng(42.443199211932985,-71.06502257287502),
        LatLng(42.44333157931081,-71.065220721066),
        LatLng(42.44345429722805,-71.0655053704977),
        LatLng(42.44359779771352,-71.06586780399084),
    ) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initLocation, 15f)
    }

    var polygonColor by remember { mutableStateOf(Color.Green) }
    var polygonWidth by remember { mutableFloatStateOf(5f) }
    var polygonFillColor by remember { mutableStateOf(Color.Green.copy(alpha = 0.3f)) }

    var polylineColor by remember { mutableStateOf(Color.Blue) }
    var polylineWidth by remember { mutableFloatStateOf(8f) }

    var showPolygonSettings by remember { mutableStateOf(false) }
    var showPolylineSettings by remember { mutableStateOf(false) }

    if (showPolygonSettings) {
        val a = settingDisplay(polygonColor,polygonWidth){
            showPolygonSettings = false
        }
        polygonWidth = a.width
        polygonColor = a.color
        polygonFillColor = a.color.copy(alpha = 0.3f)
    }
    if(showPolylineSettings){
        val a = settingDisplay(polylineColor,polylineWidth){
            showPolylineSettings = false
        }
        polylineColor = a.color
        polylineWidth = a.width
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        Polygon(
            points = parkPoints,
            fillColor = polygonFillColor,
            strokeColor = polygonColor,
            strokeWidth = polygonWidth,
            clickable = true,
            onClick = {
                showPolygonSettings = true
            }
        )
        Polyline(
            points = polylinePoints,
            color = polylineColor,
            width = polylineWidth,
            clickable = true,
            onClick = {
                showPolylineSettings = true
            }
        )
    }
}

data class Setting(val color:Color, val width: Float)

@Composable
fun settingDisplay(color:Color, width:Float,onclick:()->Unit): Setting {
    var newColor by remember { mutableStateOf(color) }
    var newWidth by remember { mutableFloatStateOf(width) }

    AlertDialog(
        onDismissRequest = { onclick()},
        title = { Text("Setting") },
        text = {
            Column {
                ColorSelection("Colors:", newColor) {
                    newColor = it
                }
                Slider(
                    value = newWidth,
                    onValueChange = { newWidth = it },
                    valueRange = 1f..20f
                )
                Text("Board Width: ${"%.1f".format(newWidth)}")
            }
        },
        confirmButton = {
            Button(onclick) {
                Text("Apply")
            }
        }
    )

    return Setting(newColor,newWidth)
}

@Composable
fun ColorSelection(label: String, currentColor: Color, onColorSelected: (Color) -> Unit) {
    var r by remember { mutableFloatStateOf(currentColor.red * 255) }
    var g by remember { mutableFloatStateOf(currentColor.green * 255) }
    var b by remember { mutableFloatStateOf(currentColor.blue * 255) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.padding(end = 8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("R", color = Color.Red, modifier = Modifier.width(30.dp))
                SingleColor(r){
                    r = it
                    onColorSelected(Color(r / 255, g / 255, b / 255))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("G", color = Color.Green, modifier = Modifier.width(30.dp))
                SingleColor(g){
                    g = it
                    onColorSelected(Color(r / 255, g / 255, b / 255))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("B", color = Color.Blue, modifier = Modifier.width(30.dp))
                SingleColor(b){
                    b = it
                    onColorSelected(Color(r / 255, g / 255, b / 255))
                }
            }
        }

        Box(
            modifier = Modifier
                .size(60.dp)
                .padding(start = 8.dp)
                .background(Color(r / 255, g / 255, b / 255))
        )
    }
}

@Composable
fun SingleColor(color:Float, onclick: (a:Float) -> Unit){
    var a = color
    Slider(
        value = a,
        onValueChange = {
            a = it
            onclick(a)
        },
        valueRange = 0f..255f,
    )
    Text("%3d".format(a.toInt()), modifier = Modifier.width(40.dp))

}