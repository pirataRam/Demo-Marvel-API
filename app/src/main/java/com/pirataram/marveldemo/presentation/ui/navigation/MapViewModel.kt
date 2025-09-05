package com.pirataram.marveldemo.presentation.ui.navigation

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val fusedClient: FusedLocationProviderClient
) : ViewModel() {

    private val _markers = MutableStateFlow<List<LatLng>>(emptyList())
    val markers: StateFlow<List<LatLng>> = _markers.asStateFlow()

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation.asStateFlow()

    private var lastEmitted: LatLng? = null

    fun addMarker(latLng: LatLng) {
        _markers.value = _markers.value + latLng
    }

    @SuppressLint("MissingPermission")
    fun fetchLastLocation() {
        fusedClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Log.d("MapViewModel", "Location fetched: latitude: ${location?.latitude} longitude: ${location?.longitude} altitude: ${location?.altitude} accuracy: ${location?.accuracy} bearing: ${location?.bearing} speed: ${location?.speed} time: ${location?.time} provider: ${location?.provider} extras: ${location?.extras} mock: ${location?.isFromMockProvider}")
                // Update user location state
                location?.let {
                    val new = LatLng(it.latitude, it.longitude)
                    if (new != lastEmitted) {   // ← evita repetidos
                        lastEmitted = new
                        _userLocation.value = new
                    }
                }
            }
    }
}