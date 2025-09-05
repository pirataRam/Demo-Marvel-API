package com.pirataram.marveldemo.presentation.ui.navigation

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

@Preview
@Composable
fun MapScreen() {
    val context = LocalContext.current
    val viewModel: MapViewModel = hiltViewModel()

    val markers by viewModel.markers.collectAsState()
    val userLoc by viewModel.userLocation.collectAsState()

    val mapView = rememberMapViewWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) viewModel.fetchLastLocation()
        else Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
    }

    SideEffect {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                viewModel.fetchLastLocation()
            }
            else -> launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Pedir ubicación al entrar
    LaunchedEffect(Unit) { viewModel.fetchLastLocation() }

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }  // 1. referencia

    AndroidView({ mapView }) { map ->
        map.getMapAsync { gMap ->
            googleMap = gMap                         // 2. guardar instancia

            // Config inicial (una sola vez)
            gMap.setOnMapLongClickListener { latLng ->
                viewModel.addMarker(latLng)
            }
        }
    }

// 3. cada vez que userLoc cambia -> mover cámara
    LaunchedEffect(userLoc) {
        userLoc?.let { latLng ->
            googleMap?.apply {
                animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
                addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title("Tu ubicación")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                )
            }
        }
    }

    LaunchedEffect(markers, googleMap) {
        googleMap?.clear()
        markers.forEach { latLng ->
            googleMap?.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Marcador")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            )
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }
    return mapView
}