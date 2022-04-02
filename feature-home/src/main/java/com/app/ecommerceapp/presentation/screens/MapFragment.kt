package com.app.ecommerceapp.presentation.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.ecommerceapp.util.constants.ResourceConstants.COLOR_DARK_BLUE
import com.app.ecommerceapp.util.extensions.changeNavigationBarColor
import com.app.ecommerceapp.util.extensions.reloadFragment
import com.app.feature_home.R
import com.app.feature_home.databinding.FragmentMapBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.app.navigation.R as NavR

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = requireNotNull(_binding)

    private lateinit var map: GoogleMap
    private var lastLocation: Location? = null

    private lateinit var resultLauncher: ActivityResultLauncher<Array<String>>

    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (!permissions.containsValue(true)) {
                Toast.makeText(requireContext(), R.string.location_denied_text, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        requireActivity().changeNavigationBarColor(color = COLOR_DARK_BLUE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lastLocation?.let { moveCameraToUser(it) }

        binding.appbarMap.btnBack.setOnClickListener {
            findNavController().navigate(NavR.id.action_mapFragment_to_homeFragment)
        }

        binding.fab.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    requiredPermissions[0]
                ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    requiredPermissions[1]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                resultLauncher.launch(requiredPermissions)
                return@setOnClickListener
            }

            lastLocation?.let { moveCameraToUser(it) }

            val locationManager =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (!isGpsEnabled) {
                Toast.makeText(
                    requireContext(),
                    R.string.location_disabled_text,
                    Toast.LENGTH_LONG
                )
                    .show()
                return@setOnClickListener
            }

            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireContext())

            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    lastLocation = location
                    map.isMyLocationEnabled = true
                    moveCameraToUser(location)
                } else {
                    reloadFragment()
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map.apply {
            generateMarkers(shops)

            setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style))
            uiSettings.apply {
                isMyLocationButtonEnabled = false
                isMapToolbarEnabled = false
            }

            moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(INIT_CAMERA_LATITUDE, INIT_CAMERA_LONGITUDE),
                    INIT_ZOOM_RADIO
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun GoogleMap.generateMarkers(locations: Map<String, LatLng>) {
        locations.forEach {
            this.addMarker(
                MarkerOptions()
                    .position(it.value)
                    .title("${it.key} Store")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            )
        }
    }

    private fun moveCameraToUser(location: Location) {
        map.apply {
            moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(location.latitude, location.longitude)
                )
            )
            animateCamera(CameraUpdateFactory.zoomTo(USER_ZOOM_RATIO), ZOOM_DURATION, null)
        }
    }

    private companion object {
        private const val INIT_CAMERA_LATITUDE = 51.91
        private const val INIT_CAMERA_LONGITUDE = 19.13
        private const val INIT_ZOOM_RADIO = 5.4f
        private const val USER_ZOOM_RATIO = 13f
        private const val ZOOM_DURATION = 1500
    }
}

private val shops = mapOf(
    "Warsaw" to LatLng(52.237049, 21.017532),
    "Krakow" to LatLng(50.049683, 19.944544),
    "Poznan" to LatLng(52.409538, 16.931992),
    "Wroclaw" to LatLng(51.107883, 17.038538),
    "Lodz" to LatLng(51.759445, 19.457216),
    "Gdansk" to LatLng(54.372158, 18.638306),
    "Lublin" to LatLng(51.246452, 22.568445),
    "Torun" to LatLng(53.013790, 18.598444),
    "Szczecin" to LatLng(53.428543, 14.552812),
    "Olsztyn" to LatLng(53.770226, 20.490189)
)