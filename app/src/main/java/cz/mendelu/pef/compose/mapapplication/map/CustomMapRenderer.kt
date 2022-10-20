package cz.mendelu.pef.compose.mapapplication.map

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import cz.mendelu.pef.compose.mapapplication.database.Place


class CustomMapRenderer(val context: Context,
                        map: GoogleMap,
                        clusterManager: ClusterManager<Place>):
    DefaultClusterRenderer<Place>(context,map, clusterManager) {

    private val icons: MutableMap<Int, Bitmap> = mutableMapOf()

    override fun onBeforeClusterItemRendered(item: Place, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)

        if(!icons.containsKey(item.type)){
            icons.put(item.type!!,
                MarkerUtil.createCustomMarkerFromLayout(context, item, false))
        }

        markerOptions.icon(BitmapDescriptorFactory
            .fromBitmap(icons[item.type]!!))
    }

    override fun shouldRenderAsCluster(cluster: Cluster<Place>): Boolean {
        return cluster.size > 10
    }
}