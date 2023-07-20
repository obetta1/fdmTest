package com.ben.papytest.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ben.papytest.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ben.papytest.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setUpFab()
        setUpBottomSheet()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getCurrentLocation()
    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.setPeekHeight(400)
    }
    private fun setUpFab(){
        binding.fabSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
   private fun setUpBottomSheet(){
       var bottomshet = findViewById<LinearLayout>(R.id.bottomSheet_LA)
       bottomSheetBehavior = BottomSheetBehavior.from(bottomshet)
       bottomSheetBehavior.isDraggable = true
    }
    private fun getCurrentLocation() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            // if the permission is not yet granted the call the request permission function
            requestLocationPermission()
        }else{
            map.isMyLocationEnabled = true
            fusedLocationClient.lastLocation.addOnCompleteListener {
                val  location = it.result //obtain the lastLocation running on the background thread

                // check if location is not empty then add a marker to the exact position
                if (location!= null){
                    val latLng = LatLng(location.latitude, location.longitude)
                    map.addMarker(MarkerOptions().position(latLng).title("you are here"))?.setIcon(
                        BitmapDescriptorFactory.fromResource(R.drawable.anthony__2_)
                    )
                    //instantial an object to spacify how the camera will be updated
                    val update = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
                    map.moveCamera(update)
                }else{
                    Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // this function is used to request for te location permission
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), //permission in the manifest
            1)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // check if the request code matches with the Request_location
        if (requestCode == 1){
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
              getCurrentLocation()
            }else{
                Toast.makeText(this, "Location Permission is Denied",  Toast.LENGTH_LONG).show()
            }
        }
    }
}
