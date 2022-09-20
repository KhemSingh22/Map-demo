package com.example.googlemap.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.googlemap.MapData
import com.example.googlemap.R
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type
import java.util.*


class MapActivity : FragmentActivity(), OnMapReadyCallback {
    private var googleMap: GoogleMap? = null

    private var originLatng: LatLng? = null
    private var destinationLatng: LatLng? = null
    lateinit var startin_point: TextView
    lateinit var find_route: Button
    lateinit var end_point: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        startin_point = findViewById<TextView>(R.id.startin_point)
        end_point = findViewById<TextView>(R.id.end_point)
        find_route = findViewById<Button>(R.id.find_route)
        // Fetching API_KEY which we wrapped
        val ai: ApplicationInfo = applicationContext.packageManager.getApplicationInfo(
            applicationContext.packageName,
            PackageManager.GET_META_DATA
        )

        val apiKey = "AIzaSyDOGwVObwVRbB7ue1rqkvyFbarZFDBHeuM"

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        startin_point.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Set the fields to specify which types of place data to
                // return after the user has made a selection.
                val field = Arrays.asList(
                    Place.Field.ID,
                    Place.Field.ADDRESS,
                    Place.Field.LAT_LNG,
                    Place.Field.NAME,
                    Place.Field.ICON_URL
                )
                val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                    .build(this@MapActivity)
                //start activity result
                startActivityForResult(intent, 101)
            }
        })
        end_point.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Set the fields to specify which types of place data to
                // return after the user has made a selection.
                val field = Arrays.asList(
                    Place.Field.ID,
                    Place.Field.ADDRESS,
                    Place.Field.LAT_LNG,
                    Place.Field.NAME,
                    Place.Field.ICON_URL
                )
                val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                    .build(this@MapActivity)
                //start activity result
                startActivityForResult(intent, 102)
            }
        })

        // Map Fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        find_route.setOnClickListener {
            mapFragment.getMapAsync {
                googleMap!!.clear()
                googleMap = it
                googleMap!!.addMarker(MarkerOptions().position(originLatng!!))
                googleMap!!.addMarker(MarkerOptions().position(destinationLatng!!))


//                val disTance =

//                Log.e("ZXZXZXZXZX", disTance.toString())


                val urll = getDirectionURL(originLatng!!, destinationLatng!!, apiKey)
                GetDirection(urll).execute()
                googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(originLatng!!, 14F))
            }
        }
    }

    override fun onMapReady(gMap: GoogleMap) {

        googleMap = gMap

        googleMap!!.clear()
        if (originLatng != null) {
            googleMap!!.addMarker(MarkerOptions().position(originLatng!!))
            googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(originLatng!!, 18F))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                //When success initialize place
                val place = Autocomplete.getPlaceFromIntent(data!!)
                //set address on edittext
                startin_point.setText(place.address)
                if (place.latLng != null) {
                    originLatng = place.latLng
                }

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status: Status = Autocomplete.getStatusFromIntent(data)
                //Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

        } else if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                //When success initialize place
                val place2 = Autocomplete.getPlaceFromIntent(data)
                end_point.setText(place2.address)
                if (place2.latLng != null) {
                    destinationLatng = place2.latLng
                }
                Log.e("SSSS", destinationLatng.toString() + "VVVVV")

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status: Status = Autocomplete.getStatusFromIntent(data)
                //Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private fun getDirectionURL(origin: LatLng, dest: LatLng, secret: String): String {
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}" +
                "&sensor=false" +
                "&mode=driving" +
                "&key=$secret"

    }

    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val latLng = LatLng((lat.toDouble() / 1E5), (lng.toDouble() / 1E5))
            poly.add(latLng)
        }
        return poly
    }

    @SuppressLint("StaticFieldLeak")
    private inner class GetDirection(val url: String) :
        AsyncTask<Void, Void, List<List<LatLng>>>() {
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {

            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            // val data = response.body().toString()
            val data = isToString(response.body().byteStream())

            val result = ArrayList<List<LatLng>>()
            Log.e("SAAAAAAS", "Working Fine......1  " + data)

            try {
                Log.e("SAAAAAAS", "Working Fine......2 ")

                val token: Type = object : TypeToken<Collection<MapData?>?>() {}.getType()
//                val respObj: Collection<MapData> =  Gson().fromJson(data, token)

                val respObj = Gson().fromJson(data, MapData::class.java)
                Log.e(
                    "SAAAAAAS",
                    "Working Fine......3 " + respObj.routes[0].legs[0].start_address.toString()
                )

                val path = ArrayList<LatLng>()
                for (i in 0 until respObj.routes[0].legs[0].steps.size) {
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()
            for (i in result.indices) {
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.GREEN)
                lineoption.geodesic(true)
            }
            googleMap!!.addPolyline(lineoption)
        }
    }

    fun isToString(inputStream: InputStream?): String? {
        val bufferSize = 1024
        val buffer = CharArray(bufferSize)
        val out = StringBuilder()
        val `in`: Reader = InputStreamReader(inputStream, "UTF-8")
        while (true) {
            val rsz: Int = `in`.read(buffer, 0, buffer.size)
            if (rsz < 0) break
            out.append(buffer, 0, rsz)
        }
        return out.toString()
    }

    fun distance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val earthRadius = 6371000.0 //meters
        val dLat = Math.toRadians((lat2 - lat1).toDouble())
        val dLng = Math.toRadians((lng2 - lng1).toDouble())
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1.toDouble())) * Math.cos(
            Math.toRadians(lat2.toDouble())
        ) *
                Math.sin(dLng / 2) * Math.sin(dLng / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return (earthRadius * c).toDouble()
    }

}
